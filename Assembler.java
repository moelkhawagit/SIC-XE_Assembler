package assembler;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.PrintWriter;
import java.util.Arrays;
import java.lang.Character;
// @author mohamedelkhawaga

public class Assembler {
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
    opt.put("LDB","68");
    opt.put("CLEAR","B4");
    opt.put("LDT","74");
    opt.put("LDB","68");
    opt.put("TIXR","B8");
    opt.put("COMPR","A0");
            }
    
    public static HashMap<String, String> optformat ;
    static{
        optformat = new HashMap<String, String>();
    optformat.put("CLEAR","2");
    optformat.put("TIXR","2");
    optformat.put("COMPR","2");
    optformat.put("A","0");
    optformat.put("X","1");
    optformat.put("L","2");
    optformat.put("B","3");
    optformat.put("S","4");
    optformat.put("T","5");
    optformat.put("F","6");
            }
     static HashMap<String, String> symtab = new HashMap<String, String>();
     
     
     
     static HashMap<String, String> littab = new HashMap<String, String>();
     
     public static String expr(String ex){
     String[] e = new String[10];
     String[] r = new String[10];
     int a =0;
     int j =0;
     String oprations = "";
     String del = "[+|-|*|/|-]";
     String del2 = "\\w";
     e = ex.split(del);
      System.out.println(Arrays.toString(e));
     r=ex.split(del2);
     while(j<r.length){
         if(!r[j].isEmpty())
             oprations += r[j];
         j++;
     }
     System.out.println(oprations);
     j=0;
     while(j<e.length){
         if(symtab.containsKey(e[j]))
             e[j] = symtab.get(e[j]);
         else if (e[j].matches("\\d+"))
             e[j] = e[j];
         else 
             return "Expression not defined";
         j++;
     }
     j=0;
     int res =0;
    
     
     while(j<e.length-1){
      
     int addresInt = Integer.parseInt(e[j],16);
     int addresInt2 = Integer.parseInt(e[j+1],16);
     if(oprations.charAt(j) == '-'){
        res = addresInt - addresInt2;
        e[j+1] = Integer.toHexString(res);
     }
     else if(oprations.charAt(j) == '+'){
        res = addresInt + addresInt2;
        e[j+1] = Integer.toHexString(res);
     }
     else 
         System.out.println("Expression Error");
     j++;
     }
     System.out.println(Arrays.toString(r));
     System.out.println(oprations);
     

      
     return e[e.length-1].toUpperCase();
     
     }
     public static String label(String l){
         
       String[] words = l.split("\\s+");
       int count = words.length;
       if(count == 3 && (words[0].length()>1))
           return words[0];
       else{ 
           System.out.println("label not found");
           return null;
                   }
      }
     public static String ilabel(String l){
         
       String[] words = l.split("\\s+");
       int count = words.length;
       if(count == 4 && (words[1].length()>1))
           return words[1];
       else{ 
           System.out.println("label not found");
           return null;
                   }
      }
     
     public static String opcode(String l){
         
       String[] words = l.split("\\s+");
       int count = words.length;
       
        if(count == 3)
           return words[1];    
       else if(count == 2)
            return words[0];
       else if(count ==1)
           return words[0];
        
       else
               { 
           System.out.println("opcode not found");
           return null;
                   }
      } 
     public static String iopcode(String l){
         
       String[] words = l.split("\\s+");
       int count = words.length;
       
        if(count == 4)
           return words[2];    
       else if(count == 3)
            return words[1];
       else if(count ==2)
           return words[1];
        
       else
               { 
           System.out.println("opcode not found");
           return null;
                   }
      }
     
     public static String operand(String l){
         
       String[] words = l.split("\\s+");
       int count = words.length;
       if(count == 3)
           return words[2];
       else if(count == 2)
            return words[1];
       else
               { 
           System.out.println("operand not found");
           return null;
                   }
      }
     public static String ioperand(String l){     
       String[] words = l.split("\\s+");
       int count = words.length;
       if(count == 4)
           return words[3];
       else if(count == 3)
            return words[2];
       else
               { 
           System.out.println("operand not found");
           return "0";
                   }
      }
     
     public static String iadres(String l){
         if(l.substring(0, 1).isEmpty()){
             System.out.println("No Address");
             return null;
         }
         else 
             return l.substring(0, 4);
     }
     
       static String loctr = "";
       static String startAdres = "";
       static  String  pleng ;
     
     public static HashMap<String, String> pass1 () throws FileNotFoundException{
          File file = new File("/Users/mohamedelkhawaga/Documents/assembler/src/assembler/input1.txt");
          File ifile = new File("/Users/mohamedelkhawaga/Documents/assembler/src/assembler/inter.txt");
          PrintWriter intr = new PrintWriter(ifile);
         Scanner l = new Scanner(file);
         String l1 = l.nextLine();
        
        if (opcode(l1).equals("START")){
            startAdres = operand(l1);
            loctr = operand(l1);
            intr.println(operand(l1)+ "\t" +l1);
            //symtab.put(label(l1),operand(l1));
        }
        
        l1 = l.nextLine();
        System.out.println(l1);
        
        
        
        while(!opcode(l1).equals("END")){
            
      System.out.println(label(l1) + "\t" +opcode(l1) +"\t" + operand(l1) + "\t" + loctr );
        if (operand(l1).startsWith("=")){
        littab.put(operand(l1), null);
        }
        if (label(l1) != null){
        if(symtab.containsKey(label(l1)))
            System.out.println("Duplicate Symbol");
        else 
            symtab.put(label(l1),frmt1(loctr));
        }
        
        
        if (opcode(l1) != null){
        if(opcode(l1).equals("EQU")){
        
        if ((!operand(l1).equals("*")) ){ //write function for expression decimal into account
            System.out.println(operand(l1));
            System.out.println(expr(operand(l1)));
            intr.println(expr(operand(l1)) + "\t" + l1);
            l1 = l.nextLine();
        continue;
        }
        }    
        intr.println(frmt1(loctr)+ "\t" +l1);
        
        
        if(opcode(l1).equals("EQU")){
        if(operand(l1).equals("*")){
            l1 = l.nextLine();
        continue;
        }
        else if ((!operand(l1).equals("*")) ){ //write function for expression decimal into account
            System.out.println(operand(l1));
            System.out.println(expr(operand(l1)));
            intr.println(expr(operand(l1)) + "\t" + l1);
            l1 = l.nextLine();
        continue;
        }
        
        }
        else if (optformat.containsKey(opcode(l1))){
            int format = Integer.parseInt(optformat.get(opcode(l1)));
            int loctr1 = Integer.parseInt(loctr, 16) + format ;
            loctr = Integer.toHexString(loctr1);
        }
        
        else if(opcode(l1).startsWith("+")){
        int loctr1 = Integer.parseInt(loctr, 16) + 4 ;
        loctr = Integer.toHexString(loctr1);
        }
        else if(opcode(l1).equals("WORD")) {
        int loctr1 = Integer.parseInt(loctr, 16) + 3 ;
        loctr = Integer.toHexString(loctr1);
        }
        else if(opcode(l1).equals("RESW")){
            int decOperand = Integer.parseInt(operand(l1))*3 + Integer.parseInt(loctr, 16);
            loctr = Integer.toHexString(decOperand);
        
        }
        else if(opcode(l1).equals("RESB")){
            int decOperand = Integer.parseInt(operand(l1)) + Integer.parseInt(loctr, 16);
            loctr = Integer.toHexString(decOperand);
        
        }
        else if (opcode(l1).equals("BYTE")){
        if(operand(l1).charAt(0) == 'C'){
        int charCount = operand(l1).length() - 3;
        int loctr1 = Integer.parseInt(loctr, 16) + charCount;
        loctr = Integer.toHexString(loctr1);
        }
        else if (operand(l1).charAt(0) == 'X'){
            int hexCount = operand(l1).length() -3;
            int loctr1 = Integer.parseInt(loctr, 16) + (hexCount/2);
            loctr = Integer.toHexString(loctr1);
        }
        }
        else{
        int loctr1 = Integer.parseInt(loctr, 16) + 3 ;
        loctr = Integer.toHexString(loctr1);
        }
        }
         l1 = l.nextLine();
         if(opcode(l1).equals("BASE")){
        System.out.println("Base found");
        intr.println("\t" + l1);
        symtab.put("BASE", operand(l1));
        l1 = l.nextLine();
        }
        else if(operand(l1).equals("LTORG")){
            System.out.println("LTORG FOUND");
       
        for(String key : littab.keySet()){
          
        if(key.charAt(1) == 'C' && littab.get(key) == null){
            littab.put(key,frmt1(loctr));
            intr.println( "\t" + l1);
            intr.println(frmt1(loctr)+ "\t" + "*" + "\t" + key);
        int charCount = key.length() - 4;
        int loctr1 = Integer.parseInt(loctr, 16) + charCount;
        loctr = Integer.toHexString(loctr1);
        }
        
        else if (key.charAt(1) == 'X' && littab.get(key) == null){
            littab.put(key,frmt1(loctr));
            intr.println( "\t" + l1);
            intr.println(frmt1(loctr)+ "\t" + "*" + "\t" + key);
            int hexCount = key.length() - 4;
            int loctr1 = Integer.parseInt(loctr, 16) + (hexCount/2);
            loctr = Integer.toHexString(loctr1);
        }    
        }
        l1 = l.nextLine();
        }
        // System.out.println(symtab.entrySet());
        // System.out.println(littab.entrySet());
        }
        intr.println("\t" + l1);
        if(symtab.containsKey("BASE")){
        String base = symtab.get(symtab.get("BASE"));
        symtab.put("BASE",base);
                }
        for(String key : littab.keySet()){
          System.out.println("LTORG FOUND");
        if(key.charAt(1) == 'C' && littab.get(key) == null){
            littab.put(key,loctr);
            intr.println(frmt1(loctr)+ "\t" + "*" + "\t" + key);
        int charCount = key.length() - 4;
        int loctr1 = Integer.parseInt(loctr, 16) + charCount;
        loctr = Integer.toHexString(loctr1);
        }
        
        else if (key.charAt(1) == 'X' && littab.get(key) == null){
            littab.put(key,loctr);
            intr.println(frmt1(loctr)+ "\t" + "*" + "\t" + key);
            int hexCount = key.length() - 4;
            int loctr1 = Integer.parseInt(loctr, 16) + (hexCount/2);
            loctr = Integer.toHexString(loctr1);
        }    
        }
         System.out.println(symtab.entrySet());
         System.out.println(littab.entrySet());
        
          pleng = Integer.toHexString(Integer.parseInt(loctr,16)-Integer.parseInt(startAdres, 16)).toUpperCase(); 
         System.out.println(pleng);
         l.close();
         intr.close();
         return symtab;
    }
     
     public String htEntry(String op, String addres){
         if(op.length() ==2 && addres.length()==4 ){
         String res = op + addres.toUpperCase();
         return res;
         }
         else {
         System.out.println("htEntry Error");
         return null;
         }
             
         
     }
     public static String frmt1(String ad){
     String  ad1 = ad.toUpperCase();
     if(ad1.length ()== 4)
         return ad1;
     else if (ad1.length()<4){
         while(ad1.length() < 4){
         ad1 = '0' + ad1;
         }
         return  ad1; }
     else if (ad1.length()>4)
         return ad1.substring(ad1.length()-4, ad1.length());
     else 
         return "FRMT1 ERROR";
     }
     public static String frmt5(String ad){
     String  ad1 = ad.toUpperCase();
     if(ad1.length ()== 5)
         return ad1;
     else if (ad1.length()<5){
         while(ad1.length() < 5){
         ad1 = '0' + ad1;
         }
         return  ad1; }
     else if (ad1.length()>5)
         return ad1.substring(ad1.length()-5, ad1.length());
     else 
         return "FRMT5 ERROR";
     }
     public static String frmt3(String ad){
     String  ad1 = ad.toUpperCase();
     if(ad1.length ()== 3)
         return ad1;
     else if (ad1.length()<3){
         while(ad1.length() < 3){
         ad1 = '0' + ad1;
         }
         return  ad1; }
     else if (ad1.length()>3)
         return ad1.substring(ad1.length()-3, ad1.length());
     else 
         return "FRMT3 ERROR";
     }
     public static String frmt2(String ad){
     String  ad1 = ad.toUpperCase();
     if(ad1.length ()== 2)
         return ad1;
     else if (ad1.length()<2){
         while(ad1.length() < 2){
         ad1 = '0' + ad1;
         }
         return  ad1; }
     
     else 
         return "FRMT2 ERROR";
     }
     public static String frmt(String ol){
     if (ol.length() == 6)
         return " " + ol;
     else if (ol.length() > 6)
         return " "  + ol.substring(0, 5);
     else if (ol.length()<6){
         while(ol.length() < 6){
         ol = '0' + ol;
         }
         return " " + ol; }
     else{
     System.out.println("Formtting Error");
     return null;
     } 
        
     }
     public static String frmt4(String ad){
     String  ad1 = ad.toUpperCase();
     if(ad1.length ()== 4)
         return ad1;
     else if (ad1.length()<4){
         while(ad1.length() < 4){
         ad1 =  ad1+"0";
         }
         return  ad1; }
     else if (ad1.length()>4)
         return ad1.substring(ad1.length()-4, ad1.length());
     else 
         return "FRMT4 ERROR";
     }
     
     public static String relcalc (String locAdd1, String oprnd ){
     int hexlocAdd = Integer.parseInt(locAdd1,16);
     int hexoprnd = Integer.parseInt(oprnd,16);
     String res = Integer.toHexString(hexoprnd-hexlocAdd).toUpperCase();
     System.out.println(res);
     return res;
     }
     
     public static boolean isPC (String adr){
         int pcmin = -2048;
         int pcmax = 2047;
         int sf = (int)Long.parseLong(adr, 16);
         System.out.println(sf);
         if (sf >= -2048 && sf < 2047)
             return true;
         else
         return false;
     }
     
     public static String baseCalc( String des){
      String disp = Integer.toHexString(Integer.parseInt(des,16)-Integer.parseInt(symtab.get("BASE"),16));
      return disp;
     }
     
     
    public static void main(String[] args) throws FileNotFoundException {
        
         
         
             pass1(); // execute pass1 function
           
//           String test = expr("BUFEND-BUFFER+RETADR");
//           System.out.println(test);
           
        //PASS 2
        
        
         File ifile = new File("/Users/mohamedelkhawaga/Documents/assembler/src/assembler/inter.txt");
         File ofile = new File("/Users/mohamedelkhawaga/Documents/assembler/src/assembler/output.txt");
         PrintWriter hte = new PrintWriter(ofile);
         Scanner ll = new Scanner(ifile);
         String l2 = ll.nextLine();
         System.out.println(ilabel(l2) + "\t" + iopcode(l2) +"\t" + ioperand(l2) );
         String pname2 = "name";
         String startAd2 = "0";
         String tstrt = "0";
         if (iopcode(l2).equals("START")){
             tstrt = ioperand(l2);
             startAd2 = frmt(ioperand(l2)); 
             pname2 = frmt(ilabel(l2));
             l2 = ll.nextLine();
         }
         System.out.println("H"+pname2+startAd2+frmt(pleng));
        hte.println("H"+pname2+startAd2+frmt(pleng));
        
        String tline[] = new String[100] ;
        String adline[] = new String[100];
        int i = 0;
        String trec=" ";
        String mrec ="M";
        hte.print("T");
        String lstAd = " ";
        while(!(iopcode(l2).equals("END"))){
            
          System.out.println(iadres(l2) + "\t"+ilabel(l2) + "\t" +iopcode(l2) +"\t" + ioperand(l2) );
          adline[i] = iadres(l2);
          
          if(iopcode(l2).startsWith("+")){                                       // HANDLING THE EXTENDED CASE 
              String op = opt.get(iopcode(l2).substring(1, iopcode(l2).length()));
              System.out.println("this is the opcode from optab "+op);
              String rmvoprnd = ioperand(l2); //@O REMOVE FIRST CHAR FROM OPERAND IF CONTAINS # OR @
              if(ioperand(l2).startsWith("#")){ // IMMEDIATE
              op = Integer.toHexString(Integer.parseInt(op, 16) + 1).toUpperCase();
              rmvoprnd = rmvoprnd.substring(1, rmvoprnd.length());
              }
              else if (ioperand(l2).startsWith("@")){//INDIRECT
              op = Integer.toHexString(Integer.parseInt(op, 16) + 2).toUpperCase();
              rmvoprnd = rmvoprnd.substring(1, rmvoprnd.length());
              mrec = mrec + frmt((Integer.toHexString(Integer.parseInt(iadres(l2),16)+1))) + "05" + "\n" + "M";
              }
              else{// OTHER CASES
              op = Integer.toHexString(Integer.parseInt(op, 16) + 3).toUpperCase();
              mrec = mrec + frmt((Integer.toHexString(Integer.parseInt(iadres(l2),16)+1))) + "05" + "\n" + "M";
              }
              
             // mrec = mrec + frmt((Integer.toHexString(Integer.parseInt(iadres(l2),16)+1))) + "05" + "\n" + "M";
              String objcode = frmt2(op) + "1" + frmt5(symtab.get(rmvoprnd));
              System.out.println(objcode);
               tline[i] = objcode;
             trec = trec + tline[i];
              l2 = ll.nextLine();
              }
          else if (ioperand(l2).equals("*")){
          mrec = mrec + frmt(iadres(l2)) + "06" + "\n" + "M";
          l2 = ll.nextLine();
          }
          else if (optformat.containsKey(iopcode(l2))){
          String objcode = opt.get(iopcode(l2));
          
          if(ioperand(l2).length() == 1)
              objcode = objcode + optformat.get(ioperand(l2));
          else {
              System.out.println(ioperand(l2).length());
              int j =0;
              while(j<3){
                  String temp = ioperand(l2).charAt(j) +"";
                  System.out.println(temp + "hama");
                 if(optformat.containsKey(temp))
                  objcode= objcode + optformat.get(temp);
                 j++;
              }
              
          }
          System.out.println(frmt4(objcode));
           tline[i] = frmt4(objcode);
             trec = trec + tline[i];
          l2 = ll.nextLine();
          }
          
          else if(opt.containsKey(iopcode(l2))){
              if(ioperand(l2).startsWith("#")){                     // HANDLE THE IMMEDIATE CASE
                 String objcode = "DEFAULT OBJECT CODE";
                 String op = opt.get(iopcode(l2));
                 op = Integer.toHexString(Integer.parseInt(op, 16) + 1).toUpperCase();
                 String rmvoprnd = ioperand(l2); 
                 rmvoprnd = rmvoprnd.substring(1, rmvoprnd.length());// REMOVE FIRST CHAR FROM OPERAND IF CONTAINS #
                 char sec = rmvoprnd.charAt(0);
                 if(Character.isDigit(sec)){
                  String disp = Integer.toHexString(Integer.parseInt(rmvoprnd,16));
                  objcode = frmt2(op)+frmt1(disp);
                  l2 = ll.nextLine();
                  }
              
                 else{
              l2 = ll.nextLine();
              if (iopcode(l2).equals("BASE")|| iopcode(l2).equals("LTORG"))
                  l2 = ll.nextLine();
              
              String disp = relcalc(iadres(l2),symtab.get(rmvoprnd));
             
              if(isPC(disp)){
              objcode = frmt2(op) + "2" + frmt3(disp);
              }
              else 
                  objcode = frmt2(op) + "4" + frmt3(baseCalc(symtab.get(rmvoprnd)));}
                  
              System.out.println(objcode);
               tline[i] = objcode;
             trec = trec + tline[i];
              continue;
              }
              else if(ioperand(l2).startsWith("@")){
                  String objcode = "DEFAULT OBJECT CODE";
                 String op = opt.get(iopcode(l2));
                 op = Integer.toHexString(Integer.parseInt(op, 16) + 2).toUpperCase();
                 String rmvoprnd = ioperand(l2); 
                 rmvoprnd = rmvoprnd.substring(1, rmvoprnd.length());// REMOVE FIRST CHAR FROM OPERAND IF CONTAINS @
                 l2 = ll.nextLine();
              if (iopcode(l2).equals("BASE")|| iopcode(l2).equals("LTORG"))
                  l2 = ll.nextLine();
              
              String disp = relcalc(iadres(l2),symtab.get(rmvoprnd));
             
              if(isPC(disp)){
              objcode = frmt2(op) + "2" + frmt3(disp);
              }
              else {
                  objcode = frmt2(op) + "4" + frmt3(baseCalc(symtab.get(rmvoprnd)));}
                  
              System.out.println(objcode);
               tline[i] = objcode;
             trec = trec + tline[i];
              }
              
              else if(ioperand(l2).endsWith(",X")){ // HANDLE INDEXED ADDRESSING 
             int count = ioperand(l2).length();
             String rmvoprnd = ioperand(l2).substring(0, count-2);
             String objcode = "DEFAULT OBJECT CODE";
                 String op = opt.get(iopcode(l2));
                 op = Integer.toHexString(Integer.parseInt(op, 16) + 3).toUpperCase();
             l2 = ll.nextLine();
              if (iopcode(l2).equals("BASE")|| iopcode(l2).equals("LTORG"))
                  l2 = ll.nextLine();
              
              String disp = relcalc(iadres(l2),symtab.get(rmvoprnd));
             
              if(isPC(disp)){
              objcode = frmt2(op) + "A" + frmt3(disp);
              }
              else {
                  objcode = frmt2(op) + "C" + frmt3(baseCalc(symtab.get(rmvoprnd)));}
                  
              System.out.println(objcode);
               tline[i] = objcode;
             trec = trec + tline[i];
             }  
              else if (ioperand(l2).startsWith("=")){
              String objcode = "DEFAULT OBJECT CODE";
                 String op = opt.get(iopcode(l2));
                 op = Integer.toHexString(Integer.parseInt(op, 16) + 3).toUpperCase();
                 String rmvoprnd = ioperand(l2); 
                 l2 = ll.nextLine();
                  if (iopcode(l2).equals("BASE") || iopcode(l2).equals("LTORG")) {
                      l2 = ll.nextLine();
                  }

                  String disp = relcalc(iadres(l2), littab.get(rmvoprnd));

                  if (isPC(disp)) {
                      objcode = frmt2(op) + "2" + frmt3(disp);
                  } else {
                      objcode = frmt2(op) + "4" + frmt3(baseCalc(littab.get(rmvoprnd)));
                  }

                  System.out.println(objcode);
              
             tline[i] = objcode;
             trec = trec + tline[i];
              }
             
             else if(symtab.containsKey(ioperand(l2))){ // GENRAL CASE WITH PC AND BASE RELATIVE 
            
            // System.out.println(opt.get(iopcode(l2)) + symtab.get(ioperand(l2)).toUpperCase());
                 String objcode = "DEFAULT OBJECT CODE";
                 String op = opt.get(iopcode(l2));
                 op = Integer.toHexString(Integer.parseInt(op, 16) + 3).toUpperCase();
                 String rmvoprnd = ioperand(l2); 
                 l2 = ll.nextLine();
                  if (iopcode(l2).equals("BASE") || iopcode(l2).equals("LTORG")) {
                      l2 = ll.nextLine();
                  }

                  String disp = relcalc(iadres(l2), symtab.get(rmvoprnd));

                  if (isPC(disp)) {
                      objcode = frmt2(op) + "2" + frmt3(disp);
                  } else {
                      objcode = frmt2(op) + "4" + frmt3(baseCalc(symtab.get(rmvoprnd)));
                  }

                  System.out.println(objcode);
              
             tline[i] = objcode;
             trec = trec + tline[i];
             
             }
             else if(iopcode(l2).equals("RSUB")){
                 System.out.println("4F0000");
                 tline[i]="4F0000";
                 trec = trec + tline[i] ;
                 l2 = ll.nextLine();
             }
            else { 
                System.out.println("Operand not found in symbol table");  
                l2 = ll.nextLine();
             } 
        }
          
          
          else if (iopcode(l2).equals("*")){
           System.out.println(ioperand(l2));
              
                  
          if(ioperand(l2).startsWith("=X") ){
                   
              String hx = ioperand(l2).substring(3, ioperand(l2).length()-1);
              System.out.println(frmt(hx));
              tline[i] = frmt(hx);
              trec = trec + tline[i];
              l2 = ll.nextLine();
          }
          
          else if (ioperand(l2).startsWith("=C") ){
          String chr = ioperand(l2).substring(3, ioperand(l2).length()-1);
          char[] ch = chr.toCharArray();
          StringBuilder builder = new StringBuilder();
          for (char c : ch) {
          int j = (int) c;
          builder.append(Integer.toHexString(j).toUpperCase());
          }
          System.out.println( builder.toString() );
          tline[i] = builder.toString();
          trec = trec + tline[i];
          l2 = ll.nextLine();
          } 
          }
          
          
          else if(iopcode(l2).equals("WORD")){
          int dec = Integer.parseInt(ioperand(l2));
          System.out.println(frmt(Integer.toHexString(dec)));
          tline[i] = frmt(Integer.toHexString(dec));
          trec = trec + tline[i];
          l2 = ll.nextLine();
          }
          
          else if (iopcode(l2).equals("BYTE") ){
              System.out.println(ioperand(l2));
              
                  
          if(ioperand(l2).startsWith("X") ){
                   
              String hx = ioperand(l2).substring(2, ioperand(l2).length()-1);
              System.out.println(frmt(hx));
              tline[i] = frmt(hx);
              trec = trec + tline[i];
              l2 = ll.nextLine();
          }
          
          else if (ioperand(l2).startsWith("C'") ){
          String chr = ioperand(l2).substring(2, ioperand(l2).length()-1);
          char[] ch = chr.toCharArray();
          StringBuilder builder = new StringBuilder();
          for (char c : ch) {
          int j = (int) c;
          builder.append(Integer.toHexString(j).toUpperCase());
          }
          System.out.println( builder.toString() );
          tline[i] = builder.toString();
          trec = trec + tline[i];
          l2 = ll.nextLine();
          }  }
          else {
              if (iopcode(l2).equals("BASE")){
                  l2 = ll.nextLine();
                  continue;}
              if (iopcode(l2).equals("LTORG")){
                  l2 = ll.nextLine();
                  continue;}
             System.out.println("Opcode must be a res");
             tline[i] = "RES";
             String leng = Integer.toHexString(Integer.parseInt(iadres(l2), 16) - Integer.parseInt(tstrt, 16));
          
          //tstrt = iadres(l2);
          System.out.println(tstrt);
          if(!trec.equals(" ")){
          hte.println(frmt(tstrt) +" " + frmt(leng) + trec + "\n");
          trec = " ";
          hte.print("T");}
          l2 = ll.nextLine();
          }
          
          
          
          if (trec.equals(" ")){
           tstrt = iadres(l2);
          }
           
          i++;
          
        } 
        
        while(ll.hasNextLine()){
            l2 = ll.nextLine();
        if (iopcode(l2).equals("*")){
           System.out.println(ioperand(l2));
              
                  
          if(ioperand(l2).startsWith("=X") ){
                   
              String hx = ioperand(l2).substring(3, ioperand(l2).length()-1);
              System.out.println(frmt(hx));
              tline[i] = frmt(hx);
              trec = trec + tline[i];
               adline[i] = iadres(l2);
              
          }
          
          else if (ioperand(l2).startsWith("=C") ){
          String chr = ioperand(l2).substring(3, ioperand(l2).length()-1);
          char[] ch = chr.toCharArray();
          StringBuilder builder = new StringBuilder();
          for (char c : ch) {
          int j = (int) c;
          builder.append(Integer.toHexString(j).toUpperCase());
          }
          System.out.println( builder.toString() );
          tline[i] = builder.toString();
          trec = trec + tline[i];
           adline[i] = iadres(l2);
          
          } 
          }
        else 
            System.out.println("AFTER END LITERAL ERROR");
        i++;
        }
        
        System.out.println(l2);
        System.out.println(Arrays.toString(tline) );
        System.out.println(Arrays.toString(adline) );
        System.out.println(trec);
        String endAd = iadres(l2);
        System.out.println(endAd + "  " + tstrt);
        String leng = Integer.toHexString(Integer.parseInt(endAd, 16) - Integer.parseInt(tstrt, 16));
        hte.print( frmt(tstrt)+" "+ frmt(leng)+ " " + trec + "\n");
        
        
        
        hte.println("E"+startAd2);
        hte.println(mrec.substring(0, mrec.length()-1).toUpperCase());
         
         
         hte.close();
         ll.close();
         
    }
    
    
}
