package com.trafficdrover.db.armor.services;

import java.util.List;


public class ArmorService {
	private static final String START_URL = "http://101.24.1.9/armor/pub/index.php";
	private static final String NAME_FORM = "login";
	
	private static final String NAME_EMAIL_FIELD = "username";
	private static final String NAME_PASS_FIELD = "password";
	private static final String INPUT_FIELD = "//input[@type='submit']";
	
	private static final String LOGIN = "u680019sov";
	private static final String PASS = "13091989";
	
	public void getOuthPage(){
		
		/*WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		
		try {
			HtmlPage autorizePage = webClient.getPage(START_URL);
			if(autorizePage != null){
				HtmlForm form = autorizePage.getFormByName(NAME_FORM);
				
				form.getInputByName(NAME_EMAIL_FIELD).setValueAttribute(LOGIN);
				form.getInputByName(NAME_PASS_FIELD).setValueAttribute(PASS);
				//System.out.println(form.asXml());
				
				//HtmlElement htmlElement = form.getInputByValue("Затвердити");
				List<HtmlElement> button = form.getElementsByAttribute("input", "type", "password");
				HtmlPage htmlPage = button.get(0).click();
				System.out.println(htmlPage.asXml());
				
				//System.out.println(button);
				//HtmlForm form2 = htmlElement.click();
				//System.out.println(form.asXml());
				//getSubmitButton(button);
				
				//System.out.println(form.asXml());
			}
			
			//System.out.println(autorizePage.asXml());
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}*/
	}
	
	/*public void getSubmitButton(List<HtmlElement> button){
		System.out.println(button.size());
		for (int i = 0; i < button.size(); i++){
			System.out.println(button.get(i));
		}
	}*/
}
