package team.sdm.service;

import java.util.List;

import team.sdm.po.Dorm;
import team.sdm.po.Student;
import team.sdm.po.StudentVo;
import team.sdm.po.Student_lack;

public interface IStudentService {

	boolean deleteStudentAndStudentDorm(long student_id);

	int StudentCount(StudentVo vo);

	List<StudentVo> studentList(StudentVo vo, int page, int rows);

	StudentVo findStudent(StudentVo vo);

	boolean addStudent(StudentVo vo);

	boolean editStudent(StudentVo vo);

	List<StudentVo> studentLackList(StudentVo vo, int page, int rows);

	int studentLackCount();

	Student login(String name, String password);

	List<Student_lack> getStudentLackList(String s_name, int page, int rows);

	int getStudentLackCount();

	Student_lack getStudent_lack(long id);

	List<String> qryIfOfFlag(String student_name);

	int updateLack(long id);

	Student getStudent(Student tempstu);

	List<Student_lack> getAllStudentLack();

	boolean inDorm(StudentVo vo, Dorm dorm);

	int updateLackFlag(long id);

	int addStudentLack(Student_lack stuLack);

	int changeStudentDorm(long dorm_id, long student_id);

	int changeStudentByChangeDorm(long student_id, long building_id, long dorm_id, String remark);

}
