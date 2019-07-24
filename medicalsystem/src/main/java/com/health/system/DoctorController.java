package com.health.system;

import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
@Autowired
DoctorService service;

@GetMapping("/chiefhealthlogin")
public ModelAndView getChiefLogin() {
	ModelAndView mv=new ModelAndView("chieflogin");
	return mv;
}
@GetMapping("/chiefregister")
public ModelAndView myChiefs() {
	ModelAndView mv=new ModelAndView("chiefregister");
	return mv;	
}

@GetMapping("/getdoctor")
public ModelAndView myHome() {
	ModelAndView mv=new ModelAndView("doctorlogin");
	return mv;
}
@GetMapping("/getchiefdoctor")
public ModelAndView myChief() {
	ModelAndView mv=new ModelAndView("chiefmedic");
	return mv;
}
@GetMapping("/getdietician")
public ModelAndView myDietician() {
	ModelAndView mv=new ModelAndView("dietlogin");
	return mv;
}
@GetMapping("/reviewdoctors")
public ModelAndView myReview() {
	ModelAndView mv=new ModelAndView();
	
	List<Reply> myList=service.getReviews();
	mv.addObject("myList",myList );
	mv.setViewName("reviewdoctors");
	return mv;
}
@GetMapping("/viewconsultations")
public ModelAndView myConsultation(@RequestParam String doctorId,
		@RequestParam String doctorphoneNumber,
		@RequestParam String doctorfirstName,
		@RequestParam String speciality) {
	ModelAndView mv=new ModelAndView();
	List<Consultation> myList=service.getConsultations(speciality);
	mv.addObject("doctorfirstName", doctorfirstName);
	mv.addObject("doctorphoneNumber", doctorphoneNumber);
	mv.addObject("doctorId", doctorId);
	mv.addObject("myList", myList);
	mv.setViewName("doctorview");
	return mv;
}
@GetMapping("/viewabuse")
public ModelAndView myAbuse() {
	ModelAndView mv=new ModelAndView();
	List<Abuse> myList=service.getAbuses();
	mv.addObject("myList", myList);
	mv.setViewName("abuseview");
	return mv;
}
@GetMapping("/deleteabuse")
public ModelAndView mybuse(@RequestParam int id) {
	ModelAndView mv=new ModelAndView();
	service.deleteAbuse(id);
	List<Abuse> myList=service.getAbuses();
	mv.addObject("myList", myList);
	mv.setViewName("abuseview");
	return mv;
}
@GetMapping("/abusedescription")
public ModelAndView myAbuses(@RequestParam String description) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("description", description);
	mv.setViewName("abusedescription");
	return mv;
}

	@RequestMapping("/viewdescription")
	public ModelAndView description(@RequestParam String description,@RequestParam String
			patientfirstName,@RequestParam String doctorfirstName,@RequestParam String
			patientphoneNumber,@RequestParam String doctorphoneNumber,@RequestParam String complainId,
			@RequestParam String specialty,@RequestParam String doctorId,@RequestParam int id) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("doctorId", doctorId);
		mv.addObject("specialty", specialty);
		mv.addObject("complainId", complainId);
		mv.addObject("doctorphoneNumber", doctorphoneNumber);
		mv.addObject("patientphoneNumber", patientphoneNumber);
		mv.addObject("doctorfirstName", doctorfirstName);
		mv.addObject("patientfirstName", patientfirstName);
		mv.addObject("description", description);
		mv.addObject("id", id);
		mv.setViewName("descriptionpage");
		return mv;
		
	}

@GetMapping("/doctorregister")
public ModelAndView myReg(@RequestParam String chiefId) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("chiefId", chiefId);
	mv.setViewName("doctorregister");
	return mv;
}

@PostMapping("/logindoctor")
public ModelAndView myLogin(@RequestParam String doctorId,@RequestParam String password) {
	Doctor doctor=service.loginDoctor(doctorId,password);
	ModelAndView mv=new ModelAndView();
	if(doctor!=null) {
	mv.addObject("doctor", doctor);
	mv.setViewName("welcomedoctor");	
	}else {
	mv.addObject("message","Invalid login details!");
	mv.setViewName("doctorlogin");	
	}
	return mv;
}

@PostMapping("/loginchief")
public ModelAndView myLoginChief(@RequestParam String chiefId,@RequestParam String password) {
	ModelAndView mv=new ModelAndView();
	ChiefHealth doctor=service.loginDoctorChief(chiefId,password);
	if(doctor!=null) {
	mv.addObject("chief", doctor);
	mv.setViewName("chiefmedic");	
	}else {
	mv.addObject("message","Invalid login details!");
	mv.setViewName("chieflogin");	
	}
	return mv;
}
@GetMapping("/abuse")
public ModelAndView abuse(@RequestParam String doctorId,@RequestParam String doctorfirstName) {
	ModelAndView mv=new ModelAndView();	
	mv.addObject("doctorId", doctorId);
	mv.addObject("doctorfirstName", doctorfirstName);
	mv.setViewName("abuses");
	return mv;
}
@PostMapping("/abuse")
public ModelAndView abuses(Abuse abuse,@RequestParam String doctorId) {
	ModelAndView mv=new ModelAndView();	
	String message=service.saveAbuse(abuse);
	mv.addObject("message",message);
	Doctor doctor=service.reloginDoctor(doctorId);
	mv.addObject("doctor", doctor);
	mv.setViewName("welcomedoctor");
	return mv;
}

@PostMapping("/viewdoctors")
public ModelAndView doctors(@RequestParam String doctorId) {
	ModelAndView mv=new ModelAndView();
	List<Doctor> myList=service.findAllDoctors(doctorId);
	mv.addObject("myList", myList);
	mv.setViewName("reviewdoctors");
	return mv;
}
@GetMapping("/viewdoctors")
public ModelAndView doctors() {
	ModelAndView mv=new ModelAndView();
	List<Doctor> myList=service.findAllDoctors();
	mv.addObject("myList", myList);
	mv.setViewName("reviewdoctors");
	return mv;
}
@GetMapping("/removedoctor")
public ModelAndView remdoctors(@RequestParam String doctorId) {
	ModelAndView mv=new ModelAndView();
	service.deleteByDid(doctorId);
	List<Doctor> myList=service.findAllDoctors();
	mv.addObject("myList", myList);
	mv.setViewName("reviewdoctors");
	
	return mv;
}
@GetMapping("/getdiethelp")
public ModelAndView myHelp(Model model) {
	ModelAndView mv=new ModelAndView("dieticianhelp");
return mv;
		
	} 
@GetMapping("/password")
public ModelAndView resetPass(@RequestParam String doctorId) {
	ModelAndView mv=new ModelAndView();	
	mv.addObject("doctorId", doctorId);
	mv.setViewName("doctorchangepassword");
	return mv;
}
@PostMapping("/doctorchange")
public ModelAndView resetPas(@RequestParam String cnewPassword,@RequestParam String doctorId,@RequestParam String newPassword) {
	ModelAndView mv=new ModelAndView();	
	Doctor doctor=service.findDoctor(doctorId);
	if(newPassword.equals(cnewPassword)) {
		doctor.setPassword(newPassword);
		service.saveDoctor(doctor);
		mv.addObject("doctor", doctor);
		mv.addObject("message", "password changed successfully!");
		mv.setViewName("welcomedoctor");
	}else {
		mv.addObject("message", "please match your password!");
		mv.setViewName("doctorchangepassword");
	}
	
	return mv;
}
@GetMapping("/changechief")
public ModelAndView resetPassW(@RequestParam String chiefId) {
	ModelAndView mv=new ModelAndView();	
	mv.addObject("chiefId", chiefId);
	mv.setViewName("chiefchangepassword");
	return mv;
}
@PostMapping("/changechief")
public ModelAndView resetPasw(@RequestParam String cnewPassword,@RequestParam String chiefId,@RequestParam String newPassword) {
	ModelAndView mv=new ModelAndView();	
	ChiefHealth chief=service.findChief(chiefId);
	if(newPassword.equals(cnewPassword)) {
		chief.setPassword(newPassword);
		service.saveDoctorChief(chief);
		mv.addObject("chief", chief);
		mv.addObject("message", "password changed successfully!");
		mv.setViewName("chiefmedic");
	}else {
		mv.addObject("message", "please match your password!");
		mv.setViewName("chiefchangepassword");
	}
	return mv;
}
@GetMapping("/changediet")
public ModelAndView resetPassWO(@RequestParam String dieticianId) {
	ModelAndView mv=new ModelAndView();	
	mv.addObject("dieticianId", dieticianId);
	mv.setViewName("dietchangepassword");
	return mv;
}
@PostMapping("/changediet")
public ModelAndView resetPasws(@RequestParam String cnewPassword,@RequestParam String dieticianId,@RequestParam String newPassword) {
	ModelAndView mv=new ModelAndView();	
	Dietician diet=service.findDietician(dieticianId);
	if(newPassword.equals(cnewPassword)){
		diet.setPassword(newPassword);
		service.saveDietician(diet);
		mv.addObject("dietician", diet);
		mv.addObject("message", "password changed successfully!");
		mv.setViewName("welcomedietician");
	}else {
		String message="match passwords please!";
		mv.addObject("message",message);
		mv.setViewName("dietchangepassword");
	}	
	
	return mv;
}
@GetMapping("/getdoctorhelp")
public  ModelAndView myHelpD(Model model) {
	ModelAndView mv=new ModelAndView("doctorhelp");
return mv;
		} 
@GetMapping("/getchiefhelp")
public ModelAndView myHelpDC(Model model) {
	ModelAndView mv=new ModelAndView("chiefhelp");	
return mv;
		} 
@GetMapping("/dietregister")
public ModelAndView mDiet(@RequestParam String chiefId) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("chiefId", chiefId);
	mv.setViewName("dietregister");
	return mv;
}
@GetMapping("/getentry")
public ModelAndView mDiety() {
	ModelAndView mv=new ModelAndView("DietEntryPage");
	return mv;
}
@PostMapping("/logindietician")
public ModelAndView myLDiet(@RequestParam String dieticianId,@RequestParam String password) {
	
	Dietician diet=service.loginDietician(dieticianId,password);
	ModelAndView mv=new ModelAndView();
	if(diet!=null) {
	mv.addObject("dietician", diet);
	mv.setViewName("welcomedietician");	
	}else {
	mv.addObject("message","Invalid login details!");
	mv.setViewName("dietlogin");	
	}
	return mv;
}
@PostMapping("/adddoctor")
public ModelAndView addDoctor(Doctor doctor,@RequestParam String chiefId) {
	ModelAndView mv=new ModelAndView();
	String doctorId=doctor.getDoctorId();
	String valid=service.check(doctorId);
	if(valid.equals("yes")) {
		mv.addObject("test","record already exists!");
		mv.setViewName("doctorregister");
	}else {
	ChiefHealth chief=service.findChief(chiefId);
	String message=service.saveDoctor(doctor);
	mv.addObject("message", message);
	mv.addObject("chief", chief);
	mv.setViewName("chiefmedic");
	}
	return mv;
}
@RequestMapping(value="/addchief",method=RequestMethod.POST)
public void addDoctorChief(@RequestBody ChiefHealth health) {
	
	service.saveDoctorChief(health);
	
}
@PostMapping("/addietician")
public ModelAndView addD(Dietician diet,@RequestParam String chiefId) {
	ModelAndView mv=new ModelAndView();
	String dieticianId=diet.getDieticianId();
	String valid=service.checkDiet(dieticianId);
	if(valid.equals("yes")) {
		mv.addObject("test","record already exists!");
		mv.setViewName("dietregister");
	}else {
	String message=service.saveDietician(diet);
	ChiefHealth chief=service.findChief(chiefId);
	mv.addObject("chief", chief);
	mv.addObject("message", message);
	mv.setViewName("chiefmedic");
	}
	return mv;
}
@GetMapping("/viewarchives")
public ModelAndView viewArchives() {
	ModelAndView mv=new ModelAndView();
	List<Archives> myList=service.findAll();
	mv.addObject("myList", myList);
	mv.setViewName("archives");
	return mv;
}
@GetMapping("/archivedescription")
public ModelAndView viewArchivesDes(@RequestParam String description) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("description", description);
	mv.setViewName("archivedes");
	return mv;
}
@GetMapping("/sendreply")
public ModelAndView reply(@RequestParam String doctorId,@RequestParam String doctorphoneNumber,
		@RequestParam String patientphoneNumber,@RequestParam String consultation,@RequestParam String doctorfirstName,
		@RequestParam String patientfirstName,@RequestParam String specialty,@RequestParam int id) {
	
	ModelAndView mv=new ModelAndView();
	mv.addObject("doctorfirstName", doctorfirstName);
	mv.addObject("patientfirstName", patientfirstName);
	mv.addObject("patientphoneNumber", patientphoneNumber);
	mv.addObject("doctorphoneNumber", doctorphoneNumber);
	mv.addObject("doctorId", doctorId);
	mv.addObject("consultation", consultation);
	mv.addObject("specialty", specialty);
	mv.addObject("id",id);
	mv.setViewName("sendreply");
	return mv;
}	
@PostMapping("/sendreply")
public ModelAndView dreply(@RequestParam String doctorId,@RequestParam String doctorphoneNumber,
		@RequestParam String patientphoneNumber,@RequestParam String doctorfirstName,@RequestParam String patientfirstName,
		@RequestParam String description,@RequestParam String specialty,@RequestParam String consultation,Reply reply
		,@RequestParam int id) {
	ModelAndView mv=new ModelAndView();
	reply.setDoctorfirstName(doctorfirstName);
	reply.setDoctorId(doctorId);
	reply.setDoctorphoneNumber(doctorphoneNumber);
	reply.setPatientphoneNumber(patientphoneNumber);
	reply.setFirstName(patientfirstName);
	reply.setSolution(description);
	String message=service.saveReply(reply);
	
	Archives arch=new Archives();
	arch.setConsultationType(specialty);
	arch.setDescription(consultation);
	arch.setFirstName(patientfirstName);
	arch.setPhoneNumber(patientphoneNumber);
	arch.setDfirstname(doctorfirstName);
	arch.setDoctorId(doctorId);
    arch.setDphoneNumber(doctorphoneNumber);
    
	service.addArchives(arch);
	
	service.deleteConsultation(id);
	
	Doctor doctor=service.reloginDoctor(doctorId);
	mv.addObject("doctor", doctor);
	mv.addObject("message", message);
	mv.setViewName("welcomedoctor");
	return mv;
}
@RequestMapping(value = "/employee-pdf-export", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
ResponseEntity<InputStreamResource> newsReport() throws IOException, DocumentException, ParseException {


    List<Archives> archives = (List<Archives>)service.findAll();

    ByteArrayInputStream byteArrayInputStream = archivesGeneratePdf(archives);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=archives.pdf");
  
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(byteArrayInputStream));
}

public ByteArrayInputStream archivesGeneratePdf(List<Archives> archives) {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {

        // For font style
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        List<String> headers = Arrays.asList("Patient", "Phone", "Doctor", "Phone", "ID","CASE");
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(90);
        table.setWidths(new int[]{3, 3, 3, 3, 3, 3});

        // Setting headers
        for (String string : headers) {
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase(string, headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
        }

        int count = 0;

        // Adding employees dynamically
        for (Archives archive : archives) {
            count ++ ;

            // For SR NO
            Font font = FontFactory.getFont(FontFactory.COURIER, 8);
            PdfPCell cell;

            // For first name
            cell = new PdfPCell(new Phrase(String.valueOf(archive.getFirstName()), font));
            cell.setPaddingLeft(2);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(archive.getPhoneNumber()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(archive.getDfirstname() ), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(archive.getDphoneNumber()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(archive.getDoctorId()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(archive.getConsultationType() ), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);
        }

        PdfWriter.getInstance(document, out);
        document.open();
        Paragraph para = new Paragraph();
        Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font font1 = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);

        
        para = new Paragraph("Hospital Archive Records", fontHeader);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);
        para = new Paragraph("General Hospital", font1);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);

        para = new Paragraph("Attended Case", fontHeader);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);

        para = new Paragraph("----", fontHeader);
        document.add(para);
        document.add(table);
        document.close();
    } catch (DocumentException ex) {
        ex.printStackTrace();
    }
    return new ByteArrayInputStream(out.toByteArray());
}
@RequestMapping(value = "/doctor-pdf-export", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
ResponseEntity<InputStreamResource> newsDoctor() throws IOException, DocumentException, ParseException {


    List<Doctor> doctor = (List<Doctor>)service.findAllDoctors();

    ByteArrayInputStream byteArrayInputStream = doctorGeneratePdf(doctor);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=doctors.pdf");
  
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(byteArrayInputStream));
}

public ByteArrayInputStream doctorGeneratePdf(List<Doctor> doctor) {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {

        // For font style
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        List<String> headers = Arrays.asList("ID", "Firstname", "Lastname", "Phone", "Speciality");
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(90);
        table.setWidths(new int[]{3, 3, 3, 3, 3});

        // Setting headers
        for (String string : headers) {
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase(string, headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
        }

        int count = 0;

        // Adding employees dynamically
        for (Doctor doctors : doctor) {
            count ++ ;

            // For SR NO
            Font font = FontFactory.getFont(FontFactory.COURIER, 8);
            PdfPCell cell;
            
            cell = new PdfPCell(new Phrase(String.valueOf(doctors.getDoctorId()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);
            // For first name
            cell = new PdfPCell(new Phrase(String.valueOf(doctors.getFirstName()), font));
            cell.setPaddingLeft(2);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(doctors.getLastName() ), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(2);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(doctors.getPhoneNumber()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);

            

            cell = new PdfPCell(new Phrase(String.valueOf(doctors.getSpeciality()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);
            
            
        }

        PdfWriter.getInstance(document, out);
        document.open();
        Paragraph para = new Paragraph();
        Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font font1 = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);

        
        para = new Paragraph("Available Doctors", fontHeader);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);
        para = new Paragraph("General Hospital", font1);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);

        para = new Paragraph("All Doctors", fontHeader);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);

        para = new Paragraph("----", fontHeader);
        document.add(para);
        document.add(table);
        document.close();
    } catch (DocumentException ex) {
        ex.printStackTrace();
    }
    return new ByteArrayInputStream(out.toByteArray());
}


}
