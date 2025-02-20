package mis.com.Naman.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mis.com.Naman.pojos.ClientWiseReport;
import mis.com.repository.MisCallDetailsSummaryRepository;
import mis.com.utils.DateUtils;

@Service
public class ConvertHTMLFormatToSendEmail {
	@Autowired
	private ClientWiseReportService clientWiseReportService;

	public String makeHtmlFormatForOBDReport(String client) throws ParseException {
		StringBuilder htmlTableBuilder = new StringBuilder();
	   	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		
		Integer totlalMsisdn = 0;
		Integer totalValidMsisdn = 0;
		Integer totlaAttemptedCalls = 0;
		Integer totalConnectedCalls = 0;
		Integer totalBillSec = 0;
		Integer totalCreditsUser = 0;
		Integer totaldigitpressed = 0;
		double totallinstenrate = 0;

			// open the table
	    htmlTableBuilder.append("<html><head>")
        .append("<style>")
        .append("table { width: 100%; border-collapse: collapse; }")
        .append("th, td { padding: 8px 12px; text-align: left; border: 1px solid #ddd; }")
        .append("th { background-color: #92a8d1; color: black; }")
        .append("tr:nth-child(even) { background-color: #f2f2f2; }")
        .append("tr:hover { background-color: #ddd; }")
        .append("h4 { color: #333; }")
        .append("</style>")
        .append("<h4>" + "Hi "+client+", Collect Your Report::<h4><br>")
        .append("</head><body>");
	    
			htmlTableBuilder.append("<table  >").append(System.lineSeparator());
			// open a rown, header row here
			htmlTableBuilder.append("\t").append("<tr >")
					.append(System.lineSeparator());
			// add the headers to the header row
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("EXECUTION_DATE").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("CLIENT_NAME").append("</th>")
			.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("USER_NAME").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("TOTAL_MSISDN").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("VALID_MSISDN").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("ATTEMPTED_CALLS").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("CONNECTED_CALLS").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("DIGIT_PRESSED").append("</th>")
			.append(System.lineSeparator());			
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("LISTEN_RATE").append("</th>")
			.append(System.lineSeparator());		
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("TOTAL_BILL_SEC").append("</th>")
			.append(System.lineSeparator());		

			htmlTableBuilder.append("\t").append("\t").append("<th>").append("CREDIT_USED").append("</th>")
					.append(System.lineSeparator());
			htmlTableBuilder.append("\t").append("\t").append("<th>").append("PANEL_NAME").append("</th>")
			.append(System.lineSeparator());
			


			// close the header row
			htmlTableBuilder.append("\t").append("</tr>").append(System.lineSeparator());
			DecimalFormat value = new DecimalFormat("#.#");
			// add a data row for each PetrolData
			List<ClientWiseReport> ClientWiseReports = clientWiseReportService.getClientWiseReports(client);
	
			System.out.println("ClientWiseReports : "+ ClientWiseReports.toString());
						for (ClientWiseReport clientWiseReport : ClientWiseReports) {
							// open a row again

							htmlTableBuilder.append("\t").append("<tr>").append(System.lineSeparator());
							// add the data
							htmlTableBuilder.append("\t").append("\t").append("<td>").append(  dateFormat.format(clientWiseReport.getExecution_date()) )
									.append("</td>").append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>").append(clientWiseReport.getClientname())
							.append("</td>").append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>").append(clientWiseReport.getUsername())
									.append("</td>").append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>")
									.append(Integer.valueOf(clientWiseReport.getTotalmsisdn()) ).append("</td>")
									.append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>")
									.append(Integer.valueOf(clientWiseReport.getValidmsisdn())).append("</td>")
									.append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>")
									.append(Integer.valueOf(clientWiseReport.getAttemptedcalls())).append("</td>")
									.append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>")
									.append(Integer.valueOf(clientWiseReport.getConnectedcalls())).append("</td>")
									.append(System.lineSeparator());
							//88888888888888888888888
							htmlTableBuilder.append("\t").append("\t").append("<td>")           
							.append(clientWiseReport.getDigitpressed()).append("</td>") 
							.append(System.lineSeparator());       
							htmlTableBuilder.append("\t").append("\t").append("<td>")           
							.append(Math.round(clientWiseReport.getListenrate())+"%").append("</td>") 
							.append(System.lineSeparator());                      
							////////////////////////////////////////////////////////////////////////
							htmlTableBuilder.append("\t").append("\t").append("<td>")
									.append(clientWiseReport.getTotalbillsec()).append("</td>")
									.append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>")
									.append(clientWiseReport.getCreditused()).append("</td>")
									.append(System.lineSeparator());
							htmlTableBuilder.append("\t").append("\t").append("<td>")
							.append(clientWiseReport.getPanelname()).append("</td>")
							.append(System.lineSeparator());

							totlalMsisdn = totlalMsisdn + (int) Math.round(Double.valueOf(clientWiseReport.getTotalmsisdn()));
							totalValidMsisdn = totalValidMsisdn
									+ (int) Math.round(Double.valueOf(clientWiseReport.getValidmsisdn()));
							totlaAttemptedCalls = totlaAttemptedCalls
									+ (int) Math.round(Double.valueOf(clientWiseReport.getAttemptedcalls()));
							totalConnectedCalls = totalConnectedCalls
									+ (int) Math.round(Double.valueOf(clientWiseReport.getConnectedcalls()));
							totalBillSec = totalBillSec + (int) Math.round(Float.valueOf(clientWiseReport.getTotalbillsec()));
							totalCreditsUser = totalCreditsUser
									+ (int) Math.round(Double.valueOf(clientWiseReport.getCreditused()));
							
							totaldigitpressed = totaldigitpressed
									+ (int) Math.round(Double.valueOf(clientWiseReport.getDigitpressed()));
							totallinstenrate = totallinstenrate
									+  clientWiseReport.getListenrate();
							
							htmlTableBuilder.append("\t").append("</tr>").append(System.lineSeparator());
						}
						htmlTableBuilder.append("\t").append("<tr >")
								.append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td colspan=\"3\">").append("Total").append("</td>")
								.append(System.lineSeparator());
				
						
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totlalMsisdn).append("</td>")
								.append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totalValidMsisdn)
								.append("</td>").append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totlaAttemptedCalls)
								.append("</td>").append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totalConnectedCalls)
								.append("</td>").append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totaldigitpressed)
						.append("</td>").append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(Math.round(totallinstenrate/ClientWiseReports.size())+"%")
						.append("</td>").append(System.lineSeparator());
						
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totalBillSec).append("</td>")
								.append(System.lineSeparator());
						htmlTableBuilder.append("\t").append("\t").append("<td>").append(totalCreditsUser)
								.append("</td>").append(System.lineSeparator());
						
						htmlTableBuilder.append("\t").append("\t").append("<td>");
//								.append(Float.valueOf(totalConnectedCalls) / totalValidMsisdn * 100))
//										+ "%")
//						if (totalValidMsisdn!=0) {
//							htmlTableBuilder.append(Double.valueOf(((totalConnectedCalls)/totalValidMsisdn)*100 )+ "%" )
//							.append("</td>");
//						}
//						else {
//							htmlTableBuilder.append(0+ "%") 
//							.append("</td>");
//						}
						
						// close the row
						htmlTableBuilder.append("\t").append("</tr>").append(System.lineSeparator());
					


			htmlTableBuilder.append("</table></body>");
		
		htmlTableBuilder.append("<head>").append("<h4>Thanks & Regards<h4>").append("</head>").append("<body>");

		// then print the result
		// System.out.println(htmlTableBuilder.toString());
		return htmlTableBuilder.toString();

	}
}
