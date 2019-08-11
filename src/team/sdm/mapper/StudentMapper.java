package team.sdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import team.sdm.po.Dorm;
import team.sdm.po.Student;
import team.sdm.po.StudentVo;
import team.sdm.po.Student_lack;

@Repository
public interface StudentMapper {

	Student login(String name, String password);

	List<Student_lack> getStudentLackList(String s_name, int start, int rows);

	int getStudentLackCount();

	Student_lack getStudent_lack(long id);

	List<String> qryIfOfFlag(String student_name);

	int updateLack(long id);

	Student getStudent(@Param("stu")Student stu);

	boolean deleteStudent(long student_id);

	void deleteStudent_Dorm(long student_id);

	public List<StudentVo> studentList(@Param("StudentVo") StudentVo vo, @Param("page") int page,
			@Param("rows") int rows);

	StudentVo findStudent(StudentVo vo);
	
	int studentCount();

	boolean addStudent(StudentVo vo);

	boolean updateStudentVo(StudentVo vo);

	public List<StudentVo> studentLackList(@Param("StudentVo") StudentVo vo, @Param("page") int page,
			@Param("rows") int rows);

	int studentLackCount();

	int inDorm(@Param("StudentVo") StudentVo vo, @Param("Dorm") Dorm dorm);

	List<Student_lack> getAllStudentLack();

	int updateLackFlag(long id);

	int addStudentLack(Student_lack stuLack);

	int changeStudentDorm(long dorm_id, long student_id);

	int changeStudentByChangeDorm(long student_id, long building_id, long dorm_id, String remark);

	int studentCount(StudentVo vo);
}
