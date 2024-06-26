package com.bingsoo.job.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bingsoo.job.dto.PostingDto;
import com.bingsoo.job.entity.Member;
import com.bingsoo.job.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {

	@Query("SELECT p FROM Posting p WHERE p.area = :area AND p.industry = :industry")
	List<Posting> findByAreaAndIndustry(@Param("area") String area, @Param("industry") String industry);

	List<Posting> findByAreaInAndIndustryIn(List<String> areas, List<String> industries);

	List<Posting> findByPostedDateBetween(LocalDate startDate, LocalDate endDate);

	void deleteById(long postCode);

	@Query(value = "SELECT  p.title, p.deadline, p.area, c.company_name, p.post_code " + "FROM Posting p "
			+ "JOIN Company c " + "ON p.cid = c.cid " + "WHERE c.cid = :username", nativeQuery = true)
	List<Object[]> postingListDtosByCid(@Param("username") String username);

	default List<PostingDto> findPostingListDtosByCid(String hid) {
		List<Object[]> db_result_list = postingListDtosByCid(hid);
		List<PostingDto> dtos = new ArrayList<>();

		for (Object[] db_result : db_result_list) {

			PostingDto dto = new PostingDto();

			dto.setTitle((String) db_result[0]);
			dto.setDeadline((Date) db_result[1]);
			dto.setArea((String) db_result[2]);
			dto.setCompany_name((String) db_result[3]);
			dto.setPost_code((Long) db_result[4]);

			dtos.add(dto);
		}

		return dtos;
	}

	List<Posting> findByCid(Member cid);

	Page<Posting> findAllByOrderByPostedDateDesc(Pageable pageable);

	@Query(value = "SELECT * FROM posting WHERE post_code = :postCode", nativeQuery = true)
	Posting findByPostCode(@Param("postCode") long postCode);

	@Query(value="SELECT * FROM posting WHERE title LIKE %:keyword% OR main_content LIKE %:keyword% ",nativeQuery = true)
	List<Posting> searchList(@Param("keyword") String keyword);

}
