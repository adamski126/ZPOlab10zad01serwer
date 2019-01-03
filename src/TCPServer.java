import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class TCPServer {

    static List<Student> students = new ArrayList<>();

    public static void generateStudents(){

        List<Integer> marks1 = new ArrayList<>();
        marks1.add(1);
        marks1.add(2);
        marks1.add(3);
        List<Integer> marks2 = new ArrayList<>();
        marks2.add(4);
        marks2.add(5);
        marks2.add(6);
        List<Integer> marks3 = new ArrayList<>();
        marks3.add(7);
        marks3.add(8);
        marks3.add(9);

        Student student1 = new Student("Jan","Kowalski","123456",marks1);
        Student student2 = new Student("Dawid","Nowak","654321",marks2);
        Student student3 = new Student("Marek","Zieliński","132457",marks3);

        students.add(student1);
        students.add(student2);
        students.add(student3);

    }



    public static void updateStudent(Student student){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getIndex().equals(student.getIndex())){
                students.set(i,student);
            }
        }
    }

    public static Student findStudent(String index){
        for(Student temp : students){
            if(temp.getIndex().equals(index)){
                return temp;
            }
        }
        return null;
    }

    public static void main(String argv[]) throws Exception {



        //generate few students
        generateStudents();


        ServerSocket ss = new ServerSocket(1201);

        while(true) {

            Socket s = ss.accept();

            try {

                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String index = br.readLine();
                Student student;





                student = findStudent(index);
                System.out.println(student);


                ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
                objectOutputStream.writeObject(student);

                ObjectInputStream objectInputStream = new ObjectInputStream(is);
                student = (Student) objectInputStream.readObject();

                updateStudent(student);



                System.out.println(students);
                //s.close();

            }catch (Exception e){
                e.printStackTrace();
                s.close();
            }

        }

    }
}
