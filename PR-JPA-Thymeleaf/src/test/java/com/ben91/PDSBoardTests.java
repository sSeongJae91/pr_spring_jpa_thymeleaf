package com.ben91;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ben91.domain.PDSBoard;
import com.ben91.domain.PDSFile;
import com.ben91.repository.PDSBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

	@Autowired
	PDSBoardRepository repo;
	
//	@Test
//	public void testInsertPDS() {
//		PDSBoard pdsb = new PDSBoard();
//		pdsb.setPname("Document");
//		
//		PDSFile file1 = new PDSFile();
//		file1.setPdsfile("file1.doc");
//		
//		PDSFile file2 = new PDSFile();
//		file2.setPdsfile("file2.doc");
//		
//		pdsb.setFiles(Arrays.asList(file1, file2));
//		
//		log.info("try to sae pds");
//		
//		repo.save(pdsb);
//	}
	
//	@Transactional
//	@Test
//	public void testUpdateFileName1() {
//		Long fno = 1L;
//		String newName = "updateFile1.doc";
//		
//		int count = repo.updatePDSFile(fno, newName);
//		
//		System.out.println("@@@@@@@@@@");
//		log.info("update count : " + count);
//	}
	
//	@Transactional
//	@Test
//	public void testUpdateFileName2() {
//		
//		String newName = "updateFile2.doc";
//		
//		Optional<PDSBoard> result = repo.findById(2L);
//		
//		result.ifPresent(pds -> {
//			log.info("데이터가 존재하므로 업데이트 시도");
//			
//			PDSFile target = new PDSFile();
//			target.setFno(2L);
//			target.setPdsfile(newName);
//			
//			int idx = pds.getFiles().indexOf(target);
//			System.out.println("idx : " + idx);
//			if(idx > -1) {
//				
//				List<PDSFile> list = pds.getFiles();
//				list.remove(idx);
//				list.add(target);
//				pds.setFiles(list);
//			}
//			repo.save(pds);
//		});
//	}
	
//	@Transactional
//	@Test
//	public void deletePDSFile() {
//		Long fno = 2L;
//		
//		int count = repo.deletePDSFile(fno);
//		
//		log.info("DELETE PDSFILE : " + count);
//	}
	
	//조인처리
//	@Test
//	public void insertDummies() {
//		
//		List<PDSBoard> list = new ArrayList<>();
//		
//		IntStream.range(1, 100).forEach(i -> {
//			
//			PDSBoard pds = new PDSBoard();
//			pds.setPname("document" + i);
//			
//			PDSFile file1 = new PDSFile();
//			file1.setPdsfile("file1.doc");
//			
//			PDSFile file2 = new PDSFile();
//			file2.setPdsfile("file1.doc");
//			
//			pds.setFiles(Arrays.asList(file1, file2));
//			
//			log.info("try to save pds");
//			
//			list.add(pds);
//		});
//		
//		repo.saveAll(list);
//	}
	
	@Test
	public void viewSummary() {
		repo.getSummary().forEach(arr -> log.info(Arrays.toString(arr)));
	}
}
