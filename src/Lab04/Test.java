/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 *
 * @author Dinanajana
 */
public class Test {
    
    
    
    public static void readFile(String [] a){
        
        Path path = Paths.get("C:\\Users\\Dinanajana\\Documents\\NetBeansProjects"
                + "\\DataStructures_Algorithms\\src\\Lab04\\Register.txt");
        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine() ;
                for(int i = 0 ; i < a.length ; i++) {
                    
                    a[i] = line;
                   
                    line = reader.readLine();
            }
        } catch (IOException x) {
            
                System.err.println(x);
        }
    
    }
    
    public static String resultsGenerator(){
    
            Random r = new Random();
            int i = r.nextInt(13);
            
            switch(i){
            
                case 1: return "A+" ;
                case 2: return "A";
                case 3: return "A-";
                case 4: return "B+";
                case 5: return "B";
                case 6: return "B-";
                case 7: return "C+";
                case 8: return "C";
                case 9: return "C-";
                case 10:return "D+";
                case 11:return  "D";
                case 12:return  "E";
                    default: return "A-";
                                    
            }
    
    }
    
    public static void main(String [] args){
    
        
        
        String [] students = new String [65] ;
        
        readFile(students);
        
        BatchNode batch = new BatchNode("E/11",69);
        
        for (int i = 0 ; i < 65 ; i++){
        
                BatchNode.StudentNode student = batch.createStudent(students[i]);
                
                for (int j = 1 ; j < 6 ; j++){
                
                    student.addCourse("CO32" + j, resultsGenerator());
                }
                
                student.addCourse("EE386", resultsGenerator());
                student.setGPA();
                batch.addStudent(student);
        
        }
        
        batch.showStudents(0);
        
        
    }
    
}
