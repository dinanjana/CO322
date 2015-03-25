
package Lab04;

import java.util.Random;

/**
 *
 * @author Dinanajana
 * 
 * 
 * 2 Nodes are present here
 * Course node and Student node
 * Each student can have a maximum results of 6 subjects
 * Each tree uses an array for its backing storage
 * Batch node contains all the students of that particular batch
 * Results will be displayed by iterating course array in inorder fashion
 * 
 * 
 * 
 */
public class BatchNode{
    
    public String batch;
    public final StudentNode [] students;
    private int size;
    private int maxIndex;
    
    public BatchNode (String batch,int no_of_students){
        
        this.size = 0;
        this.maxIndex = 0;
        this.batch = batch;
        students = new StudentNode[no_of_students*4];
    }
    
    
    public StudentNode createStudent (String name){
        
        if(students.length/2 - 1 < size){
            
            throw new IllegalStateException("Stuents full");
        
        }else{
            
            StudentNode student = new StudentNode(name);
            //students[index] = student;
            //index++;
            return student;
        } 
        
    }
    
    public void addStudent(StudentNode student){
        
        putToStudentTree(student);
    
    }
    
    public void putToStudentTree(StudentNode student){
    
                if(size == 0){
                    
                    students[0] = student;
                
                }else{
                    
                    boolean added = false;
                    int currentIndex = 0;

                    while (!added) 
                    {
                        if (student.GPA <students[currentIndex].GPA) 
                        {
                            // go left
                            if (students[currentIndex*2+1] == null) 
                            {
                                students[currentIndex*2+1] = student;
                                added = true;
                            
                                if (currentIndex*2+1 > maxIndex)
                                
                                    maxIndex = currentIndex*2+1;
                            }else
                        
                                currentIndex = currentIndex*2+1;
                        }else {
                            // go right
                            if (students[currentIndex*2+2] == null) 
                            {
                                students[currentIndex*2+2] = student;
                                added = true;
                                
                                if (currentIndex*2+2 > maxIndex)
                                maxIndex = currentIndex*2+2;
                        
                            }else
                        
                                currentIndex = currentIndex*2+2;
                        }
            
                    }
                }
            size++;

            } 
    
    
    
        public class StudentNode {
        
            public String name;
            private final Course [] weightedSub;
            private int size;
            private int maxIndex;
            public double GPA;
    
            public StudentNode(String name){
        
                this.maxIndex = 0;
                this.size = 0;
                this.weightedSub = new Course[50];
                this.name = name;
    
            }
            
            public void addCourse(String name,String result){
        
                if(size == 5) throw new IllegalStateException("Subjects full");
                
                Course course = new Course(name,result);
                putToCourseTree(course);
        //return course;
    
            }
            
            public void putToCourseTree(Course course){
    
                if(size == 0){
                    
                    weightedSub[0] = course;
                
                }else{
                    
                    boolean added = false;
                    int currentIndex = 0;

                    while (!added) 
                    {
                        if (course.weightedResult <weightedSub[currentIndex].weightedResult) 
                        {
                            // go left
                            if (weightedSub[currentIndex*2+1] == null) 
                            {
                                weightedSub[currentIndex*2+1] = course;
                                added = true;
                            
                                if (currentIndex*2+1 > maxIndex)
                                
                                    maxIndex = currentIndex*2+1;

                            }else
                        
                                currentIndex = currentIndex*2+1;
                        }else {
                            // go right
                            if (weightedSub[currentIndex*2+2] == null) 
                            {
                                weightedSub[currentIndex*2+2] = course;
                                added = true;
                                
                                if (currentIndex*2+2 > maxIndex)
                                maxIndex = currentIndex*2+2;
                        
                            }else
                        
                                currentIndex = currentIndex*2+2;
                        }
            
                    }
                }
            size++;

            }  

            public void setGPA(){
                if(size < 5){
                
                    throw new IllegalStateException("Subjects not filled");
           
                }else{
                
                    double sum = 0;
                
                    for(int i = 0 ; i < 50 ; i++){
                        
                        if(weightedSub[i]!= null){
                            sum = weightedSub[i].weightedResult+ sum;
                        
                            if("A+".equals(weightedSub[i].grade)) sum = sum - 0.000001;
                        }
                    }
       
                this.GPA = sum/18;
                
                }
            
   
            }
            
            
        
                public class Course{
        
                    String subjectname;
                    double weightedResult;
                    String grade;
                
                    public Course(String subjectname,String result){
                        this.subjectname = subjectname;
                        this.grade = result;
        
                        switch(result){
            
                            case "A+": this.weightedResult = 12.000001;
                                        break;
                            case "A" : this.weightedResult = 12;
                                        break;
                            case "A-": this.weightedResult = 3*3.7; 
                                        break;
                            case "B+": this.weightedResult = 3*3.3; 
                                        break;
                            case "B" : this.weightedResult = 3*3.0; 
                                        break;
                            case "B-": this.weightedResult = 3*2.7; 
                                        break;    
                            case "C+": this.weightedResult = 3*2.3; 
                                        break;
                            case "C" : this.weightedResult = 3*2.0; 
                                        break;
                            case "C-": this.weightedResult = 3*1.7; 
                                        break;                    
                            case "D+": this.weightedResult = 3*1.3; 
                                        break; 
                            case "D" : this.weightedResult = 3*1.0; 
                                        break;    
                            case "E" : this.weightedResult = 0.0; 
                                        break;
                        }
            
                    }
    
                }
                
                public void showGrades(int stud_index,int course_index){
                    
                    
                    if(students[stud_index].weightedSub[course_index] == null) return;
                    
                    showGrades(stud_index,2*course_index + 1);
                    
                    
                    System.out.print( students[stud_index].weightedSub[course_index].subjectname +
                            " --> " +students[stud_index].weightedSub[course_index].grade+"  ");
                   
                    showGrades(stud_index,2*course_index + 2);
                }
    
    }
        
        public void showStudents(int index){
            
            if(students[index] == null) return;
            
            showStudents(2*index+1);
            
            System.out.println("\nReg No: "+students[index].name +
                    " GPA: "+students[index].GPA);
            
            students[index].showGrades(index,0);
            
            showStudents(2*index + 2);
        
        
        }
    
        public static void main(String [] args){
        
        Random r = new Random();
            
        BatchNode batch = new BatchNode("E/11",69);
        
        
        StudentNode student = batch.createStudent("E/11/"+r.nextInt(480));
        
        student.addCourse("CO325", "B");
        student.addCourse("CO323", "A+");
        student.addCourse("CO324", "B+");
        student.addCourse("CO323", "A");
        student.addCourse("CO321", "B-");
        student.setGPA();
        batch.addStudent(student);
        
        StudentNode student1 = batch.createStudent("E/11/"+r.nextInt(480));
        
        student1.addCourse("CO325", "C");
        student1.addCourse("CO323", "C+");
        student1.addCourse("CO324", "B+");
        student1.addCourse("CO323", "A-");
        student1.addCourse("CO321", "B-");
        student1.setGPA();
        batch.addStudent(student1);
        
        StudentNode student2 = batch.createStudent("E/11/"+r.nextInt(480));
        
        student2.addCourse("CO325", "B");
        student2.addCourse("CO323", "C+");
        student2.addCourse("CO324", "B+");
        student2.addCourse("CO323", "A");
        student2.addCourse("CO321", "B-");
        student2.setGPA();
        batch.addStudent(student2);
        
        batch.showStudents(0);
        
      
    }   
}