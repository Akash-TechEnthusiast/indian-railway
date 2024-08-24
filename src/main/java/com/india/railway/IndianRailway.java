package com.india.railway;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.rule.Rule;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.rules.Fact;
import com.example.rules.RuleActivationListener;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Rectangle;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.india.railway.repository")
@EnableMongoRepositories(basePackages = "com.india.railway.repository")
@ComponentScan(basePackages = { "com.india.railway" })
@EnableTransactionManagement
@Slf4j
public class IndianRailway {

	public static void main(String[] args) {

		//// new change detected
		// again new change
		// countRulesInDroolsFile();
		// System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		SpringApplication.run(IndianRailway.class, args);

	}

	private static void countRulesInDroolsFile() {

		// Load DRL files into a KnowledgeBase
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("rule.drl"), ResourceType.DRL);
		// return 0;

		// Check for errors in DRL files
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
		}

		// Create a KnowledgeBase
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		// Create a StatefulKnowledgeSession
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		Fact fact1 = new Fact(100); // value > 10
		// Fact fact2 = new Fact(3);
		ksession.insert(fact1);

		// Create a listener to track fired rules
		RuleActivationListener activationListener = new RuleActivationListener();
		ksession.addEventListener(activationListener);

		// Insert your facts here

		// Fire all rules
		int totalrules = ksession.fireAllRules();

		Collection<Rule> allrules = ksession.getKnowledgeBase().getKnowledgePackage("com.example.rules").getRules();

		Iterator<Rule> iterator = allrules.iterator();

		while (iterator.hasNext()) {
			Rule rule = iterator.next();
		}

		System.out.println(totalrules);

		Set<String> activatedRuleNames = activationListener.getActivatedRuleNames();

		Set<String> allRuleNames = new HashSet<>();

		allRuleNames.removeAll(activatedRuleNames);

		System.out.println("Rules not fired:");
		for (String ruleName : allRuleNames) {
			System.out.println(ruleName);
		}

		ksession.dispose();

	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("ghussenaiah@gmail.com");
		mailSender.setPassword("WANAparthy@922");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return mailSender;
	}

	// @PostConstruct
	public void generatepdf() {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("awesome_pdf.pdf"));

			// Setting the event to add borders
			writer.setPageEvent(new BorderEvent());

			document.open();

			Image logo;
			try {
				logo = Image.getInstance("/home/akash/Downloads/project2024/indian-railway/logo.png");
				logo.scaleToFit(100, 100); // Resize the image
				logo.setAlignment(Element.ALIGN_CENTER); // Center the logo
				document.add(logo);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Paragraph paragraph = new Paragraph("This is a centered paragraph with borders around each page.");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			document.add(new Paragraph(" "));

			// Create a table with 3 columns
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100); // Set table width to 100% of the page width

			// Add header cells
			PdfPCell header1 = new PdfPCell(new Phrase("No"));
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header1.setPadding(2);
			PdfPCell header2 = new PdfPCell(new Phrase("Name"));
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setPadding(2);
			PdfPCell header3 = new PdfPCell(new Phrase("Designation"));
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setPadding(2);

			// Set header style
			header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header3.setBackgroundColor(BaseColor.LIGHT_GRAY);

			// Add headers to the table
			table.addCell(header1);
			table.addCell(header2);
			table.addCell(header3);

			// Add data rows
			PdfPCell row1col1 = new PdfPCell(new Phrase("1"));
			PdfPCell row1col2 = new PdfPCell(new Phrase("Akash"));
			PdfPCell row1col3 = new PdfPCell(new Phrase("Software Engineer"));

			PdfPCell row2col1 = new PdfPCell(new Phrase("2"));
			PdfPCell row2col2 = new PdfPCell(new Phrase("Rathod"));
			PdfPCell row2col3 = new PdfPCell(new Phrase("Senior Consultant"));

			row1col1.setHorizontalAlignment(Element.ALIGN_CENTER);
			row1col2.setHorizontalAlignment(Element.ALIGN_CENTER);
			row1col3.setHorizontalAlignment(Element.ALIGN_CENTER);
			row2col1.setHorizontalAlignment(Element.ALIGN_CENTER);
			row2col2.setHorizontalAlignment(Element.ALIGN_CENTER);
			row2col3.setHorizontalAlignment(Element.ALIGN_CENTER);

			row1col1.setPadding(2);
			row1col2.setPadding(2);
			row1col3.setPadding(2);
			row2col1.setPadding(2);
			row2col2.setPadding(2);
			row2col3.setPadding(2);

			table.addCell(row1col1);
			table.addCell(row1col2);
			table.addCell(row1col3);
			table.addCell(row2col1);
			table.addCell(row2col2);
			table.addCell(row2col3);

			// Add the table to the document
			document.add(table);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100); // Make the table span the width of the page

			// Left-aligned text
			PdfPCell leftCell = new PdfPCell(new Phrase("Manager sign"));
			leftCell.setBorder(Rectangle.NO_BORDER); // Remove the cell border
			leftCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align to the left

			// Right-aligned text
			PdfPCell rightCell = new PdfPCell(new Phrase("Candidate sing"));
			rightCell.setBorder(Rectangle.NO_BORDER); // Remove the cell border
			rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align to the right

			// Add the cells to the table
			table1.addCell(leftCell);
			table1.addCell(rightCell);

			// Add the table to the document
			document.add(table1);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			PdfPTable table3 = new PdfPTable(2);
			table.setWidthPercentage(100); // Make the table span the width of the page

			// Set custom widths for each column
			float[] columnWidths = { 2f, 1f }; // The first column will be twice the width of the second column
			table3.setWidths(columnWidths);

			// Create a cell with centered text and reduced size
			PdfPCell centeredCell = new PdfPCell(new Phrase("Centered text"));
			centeredCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Center horizontally
			centeredCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center vertically
			centeredCell.setFixedHeight(20); // Reduced height
			centeredCell.setPadding(2); // Reduced padding

			// Create another cell with reduced size
			PdfPCell normalCell = new PdfPCell(new Phrase("Normal text"));
			normalCell.setFixedHeight(20); // Reduced height
			normalCell.setPadding(2); // Reduced padding

			// Add the cells to the table
			table3.addCell(centeredCell); // Reduced size centered cell
			table3.addCell(normalCell); // Reduced size normal cell

			// Add the table to the document
			document.add(table3);

			document.close();

			System.out.println("PDF with border created successfully!");

		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static class BorderEvent extends PdfPageEventHelper {
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			// Get the direct content layer for drawing
			PdfContentByte canvas = writer.getDirectContent();

			// Get the page rectangle (size of the page)
			Rectangle rect = document.getPageSize();

			// Set the border properties
			float borderWidth = 2f; // Border width
			canvas.setLineWidth(borderWidth); // Set the line width

			// Draw the rectangle border with some margins
			canvas.rectangle(rect.getLeft() + 10, rect.getBottom() + 10,
					rect.getWidth() - 20, rect.getHeight() - 20);

			// Stroke the border (draw the line)
			canvas.stroke();
		}
	}

	// @PostConstruct
	public String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + URLEncoder.encode("NjM0YzYxNGIzNjQxNzg2ZjQ1NTg0NTQ5NjgzMzQ4NGU=", "UTF-8");
			String message = "&message=" + URLEncoder.encode("This is your message akash", "UTF-8");
			String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
			String numbers = "&numbers=" + URLEncoder.encode("917075459707", "UTF-8");

			// Send data
			String data = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;
			URL url = new URL(data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult = "";
			while ((line = rd.readLine()) != null) {
				// Process line...
				sResult = sResult + line + " ";
			}
			rd.close();

			return sResult;
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	public void sendMail() {
		final String username = "akash922.g@gmail.com";
		final String appPassword = "vfupuukqdhkdlyed"; // Generated App Password from Gmail

		// Setting up mail server properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		// Creating a new session with an authenticator
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, appPassword);
			}
		});
		try {
			// Creating a new email message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("akash922.g@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("akash.cloudproject@gmail.com"));
			message.setSubject("Test Email from Java App");
			message.setText("Hello, this is a test email sent from a Java application using Gmail.");

			// Sending the message
			Transport.send(message);

			System.out.println("Email sent successfully.");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
