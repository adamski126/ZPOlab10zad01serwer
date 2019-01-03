import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private String surname;
    private String index;
    private List<Integer> marks;

    Student(String name, String surname, String index, List marks){
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.marks = marks;
    }

    public void addMark(Integer mark){
        marks.add(mark);
    }

    public String getIndex(){
        return index;
    }


    @Override
    public String toString(){
        return "IMIE: " + name + " NAZWISKO: " + surname + " INDEX: " + index + " MARKS: " + marks;
    }


}
