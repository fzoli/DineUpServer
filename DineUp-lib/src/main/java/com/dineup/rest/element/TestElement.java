package com.dineup.rest.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "test")
public class TestElement {
    
    @XmlElement(name="one")
    public String one;
    
    @XmlElement(name = "lorem")
    public String lorem = "FHLFJN ÉFNHKDLÉFHDLFÉDF HJLDÉJÉNFHDKFÉD FJKPÉ FHF OÉE FEWKÉ FHEKWÉF HEWFO ÉEWF JIEWÉ FHEWJFOÉE";
    
    @XmlTransient
    public int num = 10;

    public TestElement() {
    }

    public TestElement(String one) {
        this.one = one;
    }
    
}
