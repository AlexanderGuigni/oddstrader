package com.acourse.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.acourse.base.OTGeneralMethods;

public class runtest extends OTGeneralMethods{

	@Test
	public void LeagueTabsNFL() {
		
		SelectLeagueTab("OddsButton");
		CheckHeaderAndSelectedLegue("National Football League","Betting odds & stats","NFL");
		CheckLeagueOddsTab();
		pause(2);
		SelectLeagueTab("ATS StandingsButton");
		CheckHeaderAndSelectedLegue("National Football League","ATS STANDINGS","NFL");
		CheckLeagueATSEstandingTab();
		pause(2);
		SelectLeagueTab("FuturesButton");
		CheckHeaderAndSelectedLegue("National Football League","FUTURES","NFL");
		CheckLeagueFuturesTab();
		pause(2);
		SelectLeagueTab("Power RankingsButton");
		CheckHeaderAndSelectedLegue("National Football League","POWER RANKINGS","NFL");
		CheckPwerRankingTab();
	}
	
	@Test
	public void BetSlip() {
		
		SelectLeagueTab("OddsButton");
		//Add Odd to bet Slip
		addOddToBetSlip(2,1,1);
		verifyAddedOddInBetSlip(2,1,1);
		//Add Try to add an Odd from another sportbook
		addOddToBetSlip(3,1,1);
		waitElement(By.xpath(objects.getProperty("BetSlipDialog")));
		click(By.xpath(objects.getProperty("BetSlipDialogXToClose")));
		deleteOddInBetSlip();
	}
	
	@Test
	public void NavigateLeagues() {
		
		navigateLeague("National Football League","Betting odds & stats","NFL");
		pause(2);
		navigateLeague("National Basketball Association","Betting odds & stats","NBA");
		pause(2);
		navigateLeague("Major League Baseball","Betting odds & stats","MLB");
		pause(2);
		navigateLeague("NCAA BASKETBALL","Betting odds & stats","NCAAB");
		pause(2);
		navigateLeague("NCAA FOOTBALL","Betting odds & stats","NCAAF");
		pause(2);
		navigateLeague("National Hockey League","Betting odds & stats","NHL");
		pause(2);
		
		navigateLeague("English Premier League","Betting odds", "soccer","EPL");
		pause(2);
		navigateLeague("Spanish La Liga","Betting odds", "soccer","LFP");
		pause(2);
		
		navigateLeague("Association of Tennis Professionals","Betting odds", "tennis","ATP");
		pause(2);
		navigateLeague("Women's Tennis Association","Betting odds", "tennis","WTA");
		pause(2);
		
		navigateLeague("Women's National Basketball Association","Betting odds", "other","WNBA");
		pause(2);
		navigateLeague("Ultimate Fighting Championship","Betting odds", "other","UFC");
		pause(2);
		
	}
	

}
