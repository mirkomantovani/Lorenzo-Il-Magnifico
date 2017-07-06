package it.polimi.ingsw.ps19.view;

import java.util.ArrayList;
import java.util.List;

/**
 * An asynchronous update interface for receiving notifications
 * about Input information as the Input is constructed.
 */
public interface InputObserver {
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param LeaderCardName the leader card name
	 */
	public void notifyChosenLeaderCard(String LeaderCardName);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param move the move
	 */
	public void notifyMove(String move);

	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param choices the choices
	 */
	public void notifyChosenPrivileges(String choices);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 */
	public void notifyInvalidInput();
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param actioConstructor the actio constructor
	 */
	public void notifyCouncilPalace(ArrayList<String> actioConstructor);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyTakeCardAction(ArrayList<String> actionConstructor);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param dicardedLeaderCard the dicarded leader card
	 */
	public void notifyDiscardedLeaderCard(String dicardedLeaderCard);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyMarket(ArrayList<String> actionConstructor);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyHarvest(ArrayList<String> actionConstructor);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyProduction(ArrayList<String> actionConstructor);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyCredentials(ArrayList<String> actionConstructor);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 */
	public void notifyFinishRound();
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param string the string
	 */
	public void notifyExcommunicationEffectChoice(Boolean string);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param choices the choices
	 */
	public void notifyProductionChoices(String choices);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param choices the choices
	 */
	public void notifyProductionChoices(ArrayList<Integer> choices);
	
	/**
	 * This method is called when information about an Input
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param message the message
	 */
	public void notifyChatMessage(String message);
}