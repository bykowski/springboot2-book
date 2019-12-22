package pl.bykowski.springboot2book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bykowski.springboot2book.model.Backpack;
import pl.bykowski.springboot2book.model.Notepad;
import pl.bykowski.springboot2book.model.Professor;
import pl.bykowski.springboot2book.model.Student;
import pl.bykowski.springboot2book.repo.BackpackRepo;
import pl.bykowski.springboot2book.repo.NotepadRepo;
import pl.bykowski.springboot2book.repo.ProfessorRepo;
import pl.bykowski.springboot2book.repo.StudentRepo;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Main {

    @Autowired
    public Main(StudentRepo studentRepo, BackpackRepo backpackRepo, NotepadRepo notepadRepo, ProfessorRepo professorRepo) {

        //backpacks
        Backpack backpackNike = new Backpack("Nike");
        Backpack backpackAdidas = new Backpack("Adidas");

        backpackRepo.save(backpackNike);
        backpackRepo.save(backpackAdidas);

        //notepads
        Notepad notepadHistory = new Notepad("History");
        Notepad notepadIt = new Notepad("It");
        notepadHistory.setBackpack(backpackNike);
        notepadIt.setBackpack(backpackNike);
        notepadRepo.save(notepadHistory);
        notepadRepo.save(notepadIt);

        // students
        Student studentNcb = new Student("Karol", "Zdolny", "165 NCB");
        studentNcb.setBackpack(backpackNike);
        Student studentNca = new Student("Janusz", "Leniuch", "175 NCA");
        studentNca.setBackpack(backpackAdidas);
        Set<Student> studentSet = Stream.of(studentNcb, studentNca).collect(Collectors.toSet());

        // professors
        Professor professorPh = new Professor("Jan", "Springowski", "Ph.D.");
        Professor professorProf = new Professor("Karolina", "Bazodanowska", "Prof.");
        Set<Professor> professorSet = Stream.of(professorPh, professorProf).collect(Collectors.toSet());

        professorPh.setStudentSet(studentSet);
        professorProf.setStudentSet(studentSet);

        studentNcb.setProfessorSet(professorSet);
        studentNca.setProfessorSet(professorSet);



        professorRepo.save(professorPh);
        professorRepo.save(professorProf);

        studentRepo.save(studentNcb);
        studentRepo.save(studentNca);

    }
}
