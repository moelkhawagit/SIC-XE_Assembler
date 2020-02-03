package assembler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;



/**
 *
 * @author mohamedelkhawaga
 */
public class optab {
    public static HashMap<String, String> opt ;
    static{
        opt = new HashMap<String, String>();
    opt.put("ADD","18");
    opt.put("AND","40");
    opt.put("COMP","28");
    opt.put("DIV","24");
    opt.put("J","3C");
    opt.put("JEQ","30");
    opt.put("JGT","34");
    opt.put("JLT","38");
    opt.put("JSUB","48"); 
    opt.put("LDA","00"); 
    opt.put("LDCH","50");
    opt.put("LDL","08");
    opt.put("LDX","04");
    opt.put("MUL","20");
    opt.put("OR","44");
    opt.put("RSUB","4C");
    opt.put("STA","0C");
    opt.put("STCH","54");
    opt.put("STL","14");
    opt.put("STX","10");
    opt.put("SUB","1C");
    opt.put("TD","E0");
    opt.put("TIX","2C");
    opt.put("WD","DC");
    opt.put("RD","D8");
            }
}
