import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class sort1 {

    public static void main(String[] args) {
        
        String[] arrayTxt = new String[10]; //массив в котором будут ххраниться имена фаилов, первый out остальные in
        int inArrayTxt = 0; //колличесво фаилов в программе
        int outTxt = 0; //флаг
        
        int ad = 0; //переменная отвечает за вид сортировки
        int si = 0; //перменная отвечает за тип фаилов
        
        for(int i=0; i<args.length; i++) {
            if(args[i].length()<4) {
                if(args[i].equals("-a")) System.out.println("-a found");
                else if (args[i].equals("-d")) {
                    System.out.println("-d found");
                    ad = 1; // по убыванию
                } else if (args[i].equals("-i")) System.out.println("-i found");
                else if (args[i].equals("-s")) {
                    System.out.println("-s found");
                    si =1;
                } else System.out.println("! Wrong argument : " + args[i] + "  !");
            } else outTxt = 1;
            
            if (outTxt == 1 ){
                arrayTxt[inArrayTxt] = args[i];
                inArrayTxt+=1;
            }
        }
        //вывод массива принятых имен фаилов
            System.out.println("Program files: ");
            System.out.println("  file out: " + arrayTxt[0]);
        for(int i=1; i<inArrayTxt; i++){
            System.out.println("  file in ["+i+"]: " + arrayTxt[i]);
        }
        sortControl(ad, si, arrayTxt, inArrayTxt); // метод контролирует работу программы по заданным параметрам
    }
    public static void sortControl(int ad, int si,String[] arrayTxt, int inArrayTxt ){
        System.out.println("\nMethod sortControl is working ");
        
        if (ad == 1 && si ==0) {
            System.out.println(" The program sorts integers in descending order");
            sortMainD(arrayTxt, inArrayTxt);
        }
        else if(ad == 0 && si ==0){
            System.out.println(" The program sorts integers in ascending order ");
            sortMainA(arrayTxt, inArrayTxt);
        }else if (si == 1) System.out.println(" The program does not sort the strings yet. ");
    }
    
public static void sortMainA(String[] arrayTxt, int inArrayTxt ){
        System.out.println("SortMainA is working");
        BufferedReader br = null; 
        BufferedReader br2 = null;  
        
        String line=null;
        String line2=null;
        
        int a=0;
        int b=0;
        int e=0;
        
        if (inArrayTxt==2){
            try{
                PrintWriter pw12;
                File fileOut12 = new File(arrayTxt[0]);
                if(!fileOut12.exists()) fileOut12.createNewFile();
                pw12 = new PrintWriter(fileOut12);
                
                br = new BufferedReader(new FileReader(arrayTxt[1]));
                
                line = br.readLine();
                
                while(line != null ){
                    pw12.println(line);
                    line = br.readLine();
                }
                pw12.close();
                
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Not OK with files");
                }
                
            }catch(IOException ex) {
                System.out.println("Not OK with files");
            } 
        }else{
            for(int i=1; i<inArrayTxt-1; i++) { //-1 потому что сортируем по два и последний и так зацепим
                try{
                    PrintWriter pw12;
                    if(i!=inArrayTxt-2) {
                        File fileOut12 = new File("outinter"+ i + ".txt");
                        if(!fileOut12.exists()) fileOut12.createNewFile();
                        pw12 = new PrintWriter(fileOut12);
                    } else {
                        File fileOut12 = new File(arrayTxt[0]);
                        if(!fileOut12.exists()) fileOut12.createNewFile();
                        pw12 = new PrintWriter(fileOut12);
                    } 
                    
                    if (i==1) br = new BufferedReader(new FileReader(arrayTxt[i]));
                    else br = new BufferedReader(new FileReader("outinter"+ (i-1) + ".txt"));
                    br2 = new BufferedReader(new FileReader(arrayTxt[i+1]));
                   
                    line = br.readLine();
                    e=0;
                    while(e==0 && line!=null){
                        try{
                            a = Integer.parseInt(line);
                            e = 1;
                        } catch(NumberFormatException h) {                            
                            System.out.println("Corrupted string found: ["+line+"]");
                            line = br.readLine();
                        }
                    }
                    
                    line2 = br2.readLine();
                    e=0;
                    while(e==0 && line2!=null){
                        try{
                            b = Integer.parseInt(line2);
                            e = 1;
                        } catch(NumberFormatException h) {                            
                            System.out.println("Corrupted string found: ["+line2+"]");
                            line2 = br2.readLine();
                        }
                    }

                    while((line != null ) || (line2) != null ){

                    if((line != null && a<=b) || line2 == null ) {
                        pw12.println(line);
                        line = br.readLine();
                        e=0;
                        while(e==0 && line!=null){
                            try{
                                a = Integer.parseInt(line);
                                e = 1;
                            } catch(NumberFormatException h) {                            
                                System.out.println("Corrupted string found: ["+line+"]");
                                line = br.readLine();
                            }
                        }
                    } else {
                        pw12.println(line2);
                        line2 = br2.readLine();
                        e=0;
                        while(e==0 && line2!=null){
                            try{
                                b = Integer.parseInt(line2);
                                e = 1;
                            } catch(NumberFormatException h) {                            
                                System.out.println("Corrupted string found: ["+line2+"]");
                                line2 = br2.readLine();
                            }
                        }
                    }  
                    }
                    pw12.close();
                    
                    try {
                        br.close();
                        br2.close();
                    } catch (IOException ex) {
                        System.out.println("Not OK with files");
                    }

                } catch(IOException ex) {
                    System.out.println("Not OK with files");
                }              
            }
        }  
    }

public static void sortMainD(String[] arrayTxt, int inArrayTxt ){
        System.out.println("SortMainD is working");
        BufferedReader br = null; 
        BufferedReader br2 = null;  
        
        String line=null;
        String line2=null;
        
        int a=0;
        int b=0;
        int e=0;
        
        if (inArrayTxt==2){
            try{
                PrintWriter pw12;
                File fileOut12 = new File(arrayTxt[0]);
                if(!fileOut12.exists()) fileOut12.createNewFile();
                pw12 = new PrintWriter(fileOut12);
                
                br = new BufferedReader(new FileReader(arrayTxt[1]));
                
                line = br.readLine();
                
                while(line != null ){
                    pw12.println(line);
                    line = br.readLine();
                }
                pw12.close();
                
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Not OK with files");
                }
                
            }catch(IOException ex) {
                System.out.println("Not OK with files");
            } 
        }else{
            for(int i=1; i<inArrayTxt-1; i++) { //-1 потому что сортируем по два и последний и так зацепим
                try{
                    PrintWriter pw12;
                    if(i!=inArrayTxt-2) {
                        File fileOut12 = new File("outinter"+ i + ".txt");
                        if(!fileOut12.exists()) fileOut12.createNewFile();
                        pw12 = new PrintWriter(fileOut12);
                    } else {
                        File fileOut12 = new File(arrayTxt[0]);
                        if(!fileOut12.exists()) fileOut12.createNewFile();
                        pw12 = new PrintWriter(fileOut12);
                    } 
                    
                    if (i==1) br = new BufferedReader(new FileReader(arrayTxt[i]));
                    else br = new BufferedReader(new FileReader("outinter"+ (i-1) + ".txt"));
                    br2 = new BufferedReader(new FileReader(arrayTxt[i+1]));
                   
                    line = br.readLine();
                    e=0;
                    while(e==0 && line!=null){
                        try{
                            a = Integer.parseInt(line);
                            e = 1;
                        } catch(NumberFormatException h) {                            
                            System.out.println("Corrupted string found: ["+line+"]");
                            line = br.readLine();
                        }
                    }
                    
                    line2 = br2.readLine();
                    e=0;
                    while(e==0 && line2!=null){
                        try{
                            b = Integer.parseInt(line2);
                            e = 1;
                        } catch(NumberFormatException h) {                            
                            System.out.println("Corrupted string found: ["+line2+"]");
                            line2 = br2.readLine();
                        }
                    }

                    while((line != null ) || (line2) != null ){

                    if((line != null && a>=b) || line2 == null ) {
                        pw12.println(line);
                        line = br.readLine();
                        e=0;
                        while(e==0 && line!=null){
                            try{
                                a = Integer.parseInt(line);
                                e = 1;
                            } catch(NumberFormatException h) {                            
                                System.out.println("Corrupted string found: ["+line+"]");
                                line = br.readLine();
                            }
                        }
                    } else {
                        pw12.println(line2);
                        line2 = br2.readLine();
                        e=0;
                        while(e==0 && line2!=null){
                            try{
                                b = Integer.parseInt(line2);
                                e = 1;
                            } catch(NumberFormatException h) {                            
                                System.out.println("Corrupted string found: ["+line2+"]");
                                line2 = br2.readLine();
                            }
                        }
                    }  
                    }
                    pw12.close();
                    
                    try {
                        br.close();
                        br2.close();
                    } catch (IOException ex) {
                        System.out.println("Not OK with files");
                    }

                } catch(IOException ex) {
                    System.out.println("Not OK with files");
                }              
            }
        }  
    }

}
