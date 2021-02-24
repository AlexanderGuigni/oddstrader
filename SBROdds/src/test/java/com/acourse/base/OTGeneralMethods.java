package com.acourse.base;

import java.util.Date;

import javax.mail.internet.NewsAddress;

import org.apache.poi.ss.formula.functions.Today;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.IFactoryAnnotation;

public class OTGeneralMethods extends General {
	
	public void CheckHeaderAndSelectedLegue(String Title, String Subtitle,String SelectedLeague) {
		
		//Verify league page title
		waitElement(By.xpath(objects.getProperty("LeaguePageTitle").replaceAll("LeagueName", Title)));
		//Verify league subtitle
		waitElement(By.xpath(objects.getProperty("LeaguePageSubtitle").replaceAll("Subtitle_", Subtitle)));	
		//Verify if the correct sport option is shown as selected
		waitElement(By.xpath(objects.getProperty("LeagueInLeftSideBar").replaceAll("League", SelectedLeague).concat(objects.getProperty("LeagueInLeftSideBarSelected"))));
		//Verify if region list is displayed
		click(By.xpath(objects.getProperty("RegionDropdown")));
		waitElement(By.xpath(objects.getProperty("RegionDropdownOptionsContainer")));
		
	}
	
	public void SelectLeagueTab(String LeagueTab) {
		
		click(By.xpath(objects.getProperty("LeagueTabs").replaceAll("Tab", LeagueTab)));
		//Verify if the correct league page tab is shown as selected
		waitElement(By.xpath(objects.getProperty("LeagueTabSelected").replaceAll("Tab", LeagueTab)));
	
	}
	
	public void CheckLeagueOddsTab() {
		
		//Verify Event displayed'
		waitElement(By.xpath(objects.getProperty("DataEvent").replaceAll("Index", "1")));
		//Verify if eventpage is displayed when click on event teams names
		click(By.xpath(objects.getProperty("DataEvent").replaceAll("Index", "1")));
		waitElement(By.xpath(objects.getProperty("EventPage")));
		click(By.xpath(objects.getProperty("EventPageXToClose")));
		//Verify if Market Types list is displayed
		click(By.xpath(objects.getProperty("MarketTypeDropdown")));
		waitElement(By.xpath(objects.getProperty("MarketTypeDropdownOptionsContainer")));
		//Verify if market group list is displayed
		click(By.xpath(objects.getProperty("MarketGroupDropdown")));
		waitElement(By.xpath(objects.getProperty("MarketGroupDropdownOptionsContainer")));
		
	}
	
	public void CheckLeagueATSEstandingTab() {
		
		//Verify Team Rank is displayed
		waitElement(By.xpath(objects.getProperty("TeamRank")));
		//Verify if Market Types list is displayed
		click(By.xpath(objects.getProperty("MarketTypeDropdown")));
		waitElement(By.xpath(objects.getProperty("MarketTypeDropdownOptionsContainer")));
		//Verify if market group list is displayed
		click(By.xpath(objects.getProperty("MarketGroupDropdown")));
		waitElement(By.xpath(objects.getProperty("MarketGroupDropdownOptionsContainer")));
		
	}
	
	public void CheckLeagueFuturesTab() {
		
		//Verify Future grid is displayed
		waitElement(By.xpath(objects.getProperty("FutureTeamName").replaceAll("Index", "1")));
		//Verify if futures list is displayed
		click(By.xpath(objects.getProperty("FuturesDropdown")));
		waitElement(By.xpath(objects.getProperty("FuturesDropdownOptionsContainer")));
		click(By.xpath(objects.getProperty("FuturesDropdown")));
		//Verify Line History is displayed when click in the team name
		click(By.xpath(objects.getProperty("FutureTeamName").replaceAll("Index", "1")));
		waitElement(By.xpath(objects.getProperty("LineHistory")));
		click(By.xpath(objects.getProperty("LineHitoryXToClose")));
	}
	
	public void CheckPwerRankingTab() {
		
		//Verify Future grid is displayed
		waitElement(By.xpath(objects.getProperty("PowerRankingTeam").replaceAll("Index", "1")));
		//Verify if eventpage is displayed when click on event teams names
		click(By.xpath(objects.getProperty("PowerRankingTeam").replaceAll("Index", "1")));
		waitElement(By.xpath(objects.getProperty("EventPage")));
		click(By.xpath(objects.getProperty("EventPageXToClose")));
		
	}
	
	
	public void login() {
		waitElement(By.cssSelector(objects.getProperty("TxUserName")));
		sendKeys(By.cssSelector(objects.getProperty("TxUserName")), config.getProperty("username"));
		pause(1);
		sendKeys(By.cssSelector(objects.getProperty("TxPassword")), config.getProperty("password"));
		pause(1);
	    click(By.cssSelector(objects.getProperty("ButLogIn")));
		By[] Targets = {By.cssSelector(objects.getProperty("DrDoComp")),By.cssSelector(objects.getProperty("PgeHome"))};
		log.debug("User Logged successfuly");//Adding Log
		waitOneOfTheElements(Targets);
		if (exist(By.cssSelector(objects.getProperty("DrDoComp")))){
			click(By.cssSelector(objects.getProperty("DrDoComp")));
			pause(1);
			click(By.cssSelector(objects.getProperty("CGermany")));
			pause(1);
			click(By.cssSelector(objects.getProperty("ButEnter")));
			pause(3);
			waitOverlay();
			waitElement(By.cssSelector(objects.getProperty("PgeHome")));
			log.debug("Company selected successfuly");//Adding Log
		}
		log.debug("Home page displayed");//Adding Log
		
	}
	
	String SBI;
	String EI;
	String TI;
	
	public String[] getOddInfo( Integer SportBookColumnIndex, Integer EventIndex, Integer TeamIndex) {
		
		SBI = SportBookColumnIndex.toString();
		EI = EventIndex.toString();
		TI = TeamIndex.toString();
		
		String OddInfo[] = {getAtributeValue(By.xpath(objects.getProperty("CarruselSportBook").replaceAll("Index>2", SBI)),"alt"),getTextValue(By.xpath(objects.getProperty("OddsRow").replaceAll("Index>2", SBI).replaceAll("Index1", EI).replaceAll("Index2", TI)))}; 
				//getAtributeValue(SportBook,"alt"),getTextValue(Odd)};
		return OddInfo;
		
		
	}
	
	
	public void addOddToBetSlip(Integer SportBookColumnIndex, Integer EventIndex, Integer TeamIndex) {
		
		SBI = SportBookColumnIndex.toString();
		EI = EventIndex.toString();
		TI = TeamIndex.toString();
		
		waitElement(By.xpath(objects.getProperty("OddsRow").replaceAll("Index>2", SBI).replaceAll("Index1", EI).replaceAll("Index2", TI)));
		click(By.xpath(objects.getProperty("OddsRow").replaceAll("Index>2", SBI).replaceAll("Index1", EI).replaceAll("Index2", TI)));
		
	}
	
	public void verifyAddedOddInBetSlip(Integer SportBookColumnIndex, Integer EventIndex, Integer TeamIndex) {
		
		String OddInfo[] = getOddInfo(SportBookColumnIndex,EventIndex,TeamIndex);
		click(By.xpath(objects.getProperty("BetSlipButton")));
		waitElement(By.xpath(objects.getProperty("BetSlipSelectedSportBook").replaceAll("SportBookImg", OddInfo[0])));
		waitElement(By.xpath(objects.getProperty("BetSlipAddedOdd").replaceAll("Odd", OddInfo[1])));
		click(By.xpath(objects.getProperty("BetSlipXToClose")));
		
	}
	
	public void deleteOddInBetSlip() {
		
		click(By.xpath(objects.getProperty("BetSlipButton")));
		click(By.xpath(objects.getProperty("BetSlipDeleteOddsbutton")));
		waitTheElementsIsNotDisplayed(By.xpath(objects.getProperty("BetSlipDeleteOddsbutton")),1);
		//click(By.xpath(objects.getProperty("BetSlipXToClose")));
	}
	
	public void navigateLeague(String Title, String Subtitle,String SelectedLeague) {
		
		click(By.xpath(objects.getProperty("LeagueInLeftSideBar").replaceAll("League", SelectedLeague)));
		CheckHeaderAndSelectedLegue(Title,Subtitle,SelectedLeague);
	}
	
	public void navigateLeague(String Title, String Subtitle, String Sport,String SelectedLeague) {
		
		scrollIntoView(By.xpath(objects.getProperty("LeagueInLeftSideBar").replaceAll("League", Sport)));
		click(By.xpath(objects.getProperty("LeagueInLeftSideBar").replaceAll("League", Sport)));
		click(By.xpath(objects.getProperty("LeagueInContainer").replaceAll("League", SelectedLeague)));
		CheckHeaderAndSelectedLegue(Title,Subtitle,Sport);
	}
	
	
	
}
