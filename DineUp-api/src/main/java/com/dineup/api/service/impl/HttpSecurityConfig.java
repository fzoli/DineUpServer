package com.dineup.api.service.impl;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Security config used by HTTP service.
 *
 */
public final class HttpSecurityConfig {
    private final X509HostnameVerifier mHostNameVerifier;
    private final SSLSocketFactory mSocketFactory;

    static final String ENABLED_CIPHERS[] = {
        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
        "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
        "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
        "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
        "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
        "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
        "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
        "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
        "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
        "TLS_RSA_WITH_AES_128_CBC_SHA",
        "TLS_RSA_WITH_AES_256_CBC_SHA",
        "SSL_RSA_WITH_3DES_EDE_CBC_SHA",
        "SSL_RSA_WITH_RC4_128_SHA",
        "SSL_RSA_WITH_RC4_128_MD5",
    };

    /**
     * Factory method, create new allow-all security config. 
     * @return Security config.
     */
    public static HttpSecurityConfig newAllowAllSecurityConfig() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        return new HttpSecurityConfig(
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER,
                new CustomSocketFactory(keyStore, new TrustManager[]{new AllowAllTrustManager()}));
    }
    
    /**
     * Factory method, create new strict security config.
     * @return Security config.
     */
    public static HttpSecurityConfig newStrictSecurityConfig() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        return newStrictSecurityConfig(null);
    }
    
    /**
     * Factory method, create new strict security config.
     * @return Security config.
     */
    public static HttpSecurityConfig newStrictSecurityConfig(X509HostnameVerifier hostnameVerifier) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        if (hostnameVerifier == null) {
            hostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
        }
        SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        return new HttpSecurityConfig(
                hostnameVerifier,
                sslSocketFactory);
    }

    /**
     * Factory method, create new custom security config from keystore. 
     * @return Security config.
     */
    public static HttpSecurityConfig newCustomConfig(KeyStore keyStore) throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException, KeyManagementException, UnrecoverableKeyException {
        return newCustomConfig(keyStore, null);
    }
    
    /**
     * Factory method, create new custom security config from keystore. 
     * @return Security config.
     */
    public static HttpSecurityConfig newCustomConfig(KeyStore keyStore, X509HostnameVerifier hostnameVerifier) throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException, KeyManagementException, UnrecoverableKeyException {
        if (hostnameVerifier == null) {
            hostnameVerifier = SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        }
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        
        return new HttpSecurityConfig(
                hostnameVerifier,
                new CustomSocketFactory(keyStore, tmf.getTrustManagers()));
    }

    public SSLSocketFactory getSocketFactory() {
        return mSocketFactory;
    }

    public X509HostnameVerifier getHostNameVerifier() {
        return mHostNameVerifier;
    }
    
    private HttpSecurityConfig(X509HostnameVerifier hostNameVerifier, SSLSocketFactory socketFactory) {
        mHostNameVerifier = hostNameVerifier;
        mSocketFactory = socketFactory;
        mSocketFactory.setHostnameVerifier(mHostNameVerifier);
    }
    
    private static class CustomSocketFactory extends SSLSocketFactory {
        final SSLContext mSslContext;
        public CustomSocketFactory(KeyStore keyStore, TrustManager[] trustManagers) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            mSslContext = SSLContext.getInstance("TLS");
            mSslContext.init(null, trustManagers, new java.security.SecureRandom());
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            SSLSocket s = (SSLSocket)mSslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
            setEnabledCiphers(s);
            return s;
        }

        @Override
        public Socket createSocket() throws IOException {
            SSLSocket s = (SSLSocket) mSslContext.getSocketFactory().createSocket();
            setEnabledCiphers(s);
            return s;
        }

        private void setEnabledCiphers(SSLSocket s) {
            List<String> ciphers = new ArrayList<String>();
            for (String cipher : ENABLED_CIPHERS) {
                if (isCipherSupported(s, cipher)) {
                    ciphers.add(cipher);
                }
            }
            String[] cipherArray = new String[ciphers.size()];
            ciphers.toArray(cipherArray);
            s.setEnabledCipherSuites(cipherArray);
        }

        private boolean isCipherSupported(SSLSocket s, String cipher) {
            for (String c : s.getSupportedCipherSuites()) {
                if (c.equals(cipher)) {
                    return true;
                }
            }
            return false;
        }

    }
    
    private static class AllowAllTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        
    }
    

    /**
     * Hostname verifier that allow any CN match for *.cellum.com.
     * For other hosts it uses STRICT_HOSTNAME_VERIFIER.
     *
     */
    @SuppressWarnings("unused")
    private static class RnDHostnameVerifier implements X509HostnameVerifier {
        
        private static final X509HostnameVerifier mStrictVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
        
        private boolean isHostWithRnDCertificate(String host) {
            if(host.endsWith(".cellum.com")) {
                return true;
            }
            return false;
        }
        
        @Override
        public boolean verify(String host, SSLSession session) {
            if(isHostWithRnDCertificate(host)) {
                return true;
            }
            return mStrictVerifier.verify(host, session);
        }

        @Override
        public void verify(String host, SSLSocket ssl) throws IOException {
            if(isHostWithRnDCertificate(host)) {
                return;
            }
            mStrictVerifier.verify(host, ssl);
        }

        @Override
        public void verify(String host, X509Certificate cert)
                throws SSLException {
            if(isHostWithRnDCertificate(host)) {
                return;
            }            
            mStrictVerifier.verify(host, cert);
        }

        @Override
        public void verify(String host, String[] cns, String[] subjectAlts)
                throws SSLException {
            if(isHostWithRnDCertificate(host)) {
                return;
            }            
            mStrictVerifier.verify(host, cns, subjectAlts);
        }        
    }

}
