package com.health.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/hospital")
public class MedicalController {
	
@Autowired
PatientService service;

@Autowired
DietService service1;

@RequestMapping("/home")
public ModelAndView home() {
	ModelAndView mv=new ModelAndView("home");
	return mv;
}
@RequestMapping("/register")
public ModelAndView register() {
	ModelAndView mv=new ModelAndView("patientregister");
	return mv;
}
@RequestMapping("/patientlogin")
public ModelAndView login() {
	ModelAndView mv=new ModelAndView("patientlogin");
	return mv;
}
@RequestMapping("/welcome")
public ModelAndView welcome() {
	ModelAndView mv=new ModelAndView("welcomepatient");
	return mv;
}
@PostMapping("/addpatient")
public ModelAndView addpatient(Patient patient) {
	ModelAndView mv=new ModelAndView();
	String phoneNumber=patient.getPhoneNumber();
	String message=service.check(phoneNumber);
	if(message.equals("yes")) {
		mv.addObject("text","your record exists!");
		mv.setViewName("patientregister");
	}else {
    service.savePatient(patient);
    mv.setViewName("patientlogin");
	}
	return mv;
}
@GetMapping("/viewhelp")
public ModelAndView helppatient() {
	ModelAndView mv=new ModelAndView();
    mv.setViewName("patienthelp");
	return mv;
}
@GetMapping("/viewpatients")
public ModelAndView helppatients() {
	ModelAndView mv=new ModelAndView();
	List<Patient> myList=service.findAllPatients();
	mv.addObject("myList",myList);
    mv.setViewName("viewpatients");
	return mv;
}
@PostMapping("/viewpatients")
public ModelAndView helpatient(@RequestParam String phoneNumber) {
	ModelAndView mv=new ModelAndView();
	List<Patient> myList=service.findAllPatients(phoneNumber);
	mv.addObject("myList",myList);
    mv.setViewName("viewpatients");
	return mv;
}
@GetMapping("/removepatient")
public ModelAndView rempatients(@RequestParam String phoneNumber) {
	ModelAndView mv=new ModelAndView();
	service.deletePatients(phoneNumber);
	List<Patient> myList=service.findAllPatients();
	mv.addObject("myList",myList);
    mv.setViewName("viewpatients");
	return mv;
}
@PostMapping("/loginpatient")
public ModelAndView loginpatient(@RequestParam String phoneNumber,@RequestParam String password) {
	ModelAndView mv=new ModelAndView();
    Patient pt=service.loginPatient(phoneNumber,password);
    if(pt!=null) {
    	mv.addObject("patient", pt);
    	mv.addObject("general", "General");
    	mv.addObject("optical", "Optical");
    	mv.addObject("dental", "Dental");
    	mv.addObject("paediatric", "Paediatric");
    	mv.addObject("ent","E.N.T");
    	mv.addObject("cancer", "Cancer");
    	mv.addObject("aids", "AIDS");
    	mv.addObject("ulcers","Ulcers");
    	mv.addObject("hypertension", "Hypertension");
    	mv.addObject("athritis", "Athritis");
    	mv.addObject("expectancy", "Expectancy");
    	mv.addObject("obesity", "Obesity");
    	mv.addObject("diabetes", "Diabetes");
    	mv.setViewName("welcomepatient");
    }
    else {
    	mv.addObject("message", "invalid login details!");
    	mv.setViewName("patientlogin");
    }
    
	return mv;
}
@RequestMapping("/consult")
public ModelAndView consult(@RequestParam String firstName,@RequestParam String lastName,
		@RequestParam String phoneNumber,@RequestParam int age,@RequestParam String type) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("type", type);
	mv.addObject("firstName", firstName);
	mv.addObject("lastName", lastName);
	mv.addObject("age", age);
	mv.addObject("phoneNumber", phoneNumber);
	mv.setViewName("consult");
	return mv;
}
@PostMapping("/consult")
public ModelAndView consultation(@RequestParam Integer age,@RequestParam String consultationType,
		@RequestParam String firstName,@RequestParam String lastName,@RequestParam String phoneNumber,
		Consultation consult) {
	ModelAndView mv=new ModelAndView();
	
	consult.setAge(age);
	consult.setConsultationType(consultationType);
	consult.setFirstName(firstName);
	consult.setLastName(lastName);
	consult.setPhoneNumber(phoneNumber);
	String message=service.saveConsult(consult);
	Patient pt=service.findPatient(phoneNumber);
	mv.addObject("patient",pt);
	mv.addObject("message", message);
	mv.addObject("general", "General");
	mv.addObject("optical", "Optical");
	mv.addObject("dental", "Dental");
	mv.addObject("paediatric", "Paediatric");
	mv.addObject("ent","E.N.T");
	mv.addObject("cancer", "Cancer");
	mv.addObject("aids", "AIDS");
	mv.addObject("ulcers","Ulcers");
	mv.addObject("hypertension", "Hypertension");
	mv.addObject("athritis", "Athritis");
	mv.addObject("expectancy", "Expectancy");
	mv.addObject("obesity", "Obesity");
	mv.addObject("diabetes", "Diabetes");
	mv.setViewName("welcomepatient");
	return mv;
}
@RequestMapping("/dietentry")
public String enterDiet() {
	
return "DietEntryPage";
}
@RequestMapping("/savediet")
public String saveDiet(Diet diet,Model model) {
String message=service1.saveDiet(diet);
	model.addAttribute("message", message);
	return "DietEntryPage";
	
} 
@GetMapping("/dietdisplay")
public ModelAndView display(String condition) {
	String message="Diet for the condition doesn't exist";
	ModelAndView mv=new ModelAndView();
	Diet diet=new Diet();
diet=service1.getDiet(condition);
if(diet!=null) {
mv.addObject("diet", diet);
mv.setViewName("diets");
}else {
	mv.addObject("message",message);
	mv.setViewName("unattendedreply");
}
return mv;
}
@GetMapping("/viewreply")
public ModelAndView viewReply(@RequestParam String phoneNumber) {
	ModelAndView mv=new ModelAndView();
  List<Reply> myList=service.getReply(phoneNumber);
	if(myList!=null) {
	mv.addObject("myList", myList);
	mv.setViewName("viewreply");
	}else {
		mv.addObject("message", "not yet attended to");
		mv.setViewName("unattendedreply");
	}
	return mv;

}
@GetMapping("/solution")
public ModelAndView viewSoution(@RequestParam String solution) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("solution",solution);
	mv.setViewName("solution");
	return mv;
	
}
@GetMapping("/password")
public ModelAndView resetPass(@RequestParam String phoneNumber) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("phoneNumber", phoneNumber);
	mv.setViewName("changepassword");
	return mv;
}
@PostMapping("/change")
public ModelAndView resetPas(@RequestParam String phoneNumber,
		@RequestParam String newPassword,@RequestParam String cnewPassword,Patient patient) {
	ModelAndView mv=new ModelAndView();	
			patient=service.findPatient(phoneNumber);
	if(!(cnewPassword.equals(newPassword))) {
	   
		mv.addObject("message", "match password please");
		mv.setViewName("changepassword");
		}
	else
		
	{
			patient.setPassword(newPassword);
			service.savePatient(patient);
			mv.addObject("patient",patient);
			mv.addObject("message", "password changed successfully!");
			mv.addObject("optical", "Optical");
			mv.addObject("dental", "Dental");
			mv.addObject("paediatric", "Paediatric");
			mv.addObject("ent","E.N.T");
			mv.addObject("cancer", "Cancer");
			mv.addObject("aids", "AIDS");
			mv.addObject("ulcers","Ulcers");
			mv.addObject("hypertension", "Hypertension");
			mv.addObject("athritis", "Athritis");
			mv.addObject("expectancy", "Expectancy");
			mv.addObject("obesity", "Obesity");
			mv.addObject("diabetes", "Diabetes");
			mv.setViewName("welcomepatient");	
			
		
	}
	
	return mv;
}
@RequestMapping(value = "/patient-pdf-export", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
ResponseEntity<InputStreamResource> newsPatients() throws IOException, DocumentException, ParseException {


    List<Patient> patient = (List<Patient>)service.findAllPatients();

    ByteArrayInputStream byteArrayInputStream = patientGeneratePdf(patient);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=patient.pdf");
  
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(byteArrayInputStream));
}

public ByteArrayInputStream patientGeneratePdf(List<Patient> patient) {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {

        // For font style
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        List<String> headers = Arrays.asList("Firstname", "Lastname", "Phone", "Age");
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(90);
        table.setWidths(new int[]{3, 3, 3, 3});

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
        for (Patient patients : patient) {
            count ++ ;

            // For SR NO
            Font font = FontFactory.getFont(FontFactory.COURIER, 8);
            PdfPCell cell;
            
            // For first name
            cell = new PdfPCell(new Phrase(String.valueOf(patients.getFirstName()), font));
            cell.setPaddingLeft(2);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(patients.getLastName() ), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(2);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(patients.getPhoneNumber()), font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(2);
            table.addCell(cell);

            

            cell = new PdfPCell(new Phrase(String.valueOf(patients.getAge()), font));
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

        
        para = new Paragraph("Hospital Patients", fontHeader);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);
        para = new Paragraph("General Hospital", font1);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);

        para = new Paragraph("All Patients", fontHeader);
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
