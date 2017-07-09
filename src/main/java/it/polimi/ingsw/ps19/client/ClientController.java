package it.polimi.ingsw.ps19.client;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.command.toserver.ActivateLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChatMessageClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.toserver.ChurchSupportCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.DiscardLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.FinishRoundCommand;
import it.polimi.ingsw.ps19.command.toserver.HarvestCommand;
import it.polimi.ingsw.ps19.command.toserver.InvalidInputCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.toserver.PlayerMoveCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionActivationCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SatanChoiceCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.command.toserver.TakeCardCommand;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.InputObserver;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * This class represents the client controller that takes care of sending
 * through the network all the commands requested by the player.
 * 
 * @author Jimmy
 *
 */
public class ClientController implements InputObserver {

	/** The user interface. */
	private UserInterface userInterface;
	
	/** The network interface. */
	private NetworkInterface networkInterface;
	
	/** The command handler. */
	private ClientCommandHandler commandHandler;
	
	/** The player color. */
	private String playerColor;

	/**
	 * Instantiates a new client controller.
	 *
	 * @param networkInterface the network interface
	 */
	public ClientController(NetworkInterface networkInterface) {
		this.networkInterface = networkInterface;
	}

	/**
	 * Send command.
	 *
	 * @param command the command
	 */
	private void sendCommand(ClientToServerCommand command) {
		try {
			networkInterface.sendCommand(command);

			System.out.println("clientcontro: invio comando al server");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyChosenLeaderCard(java.lang.String)
	 */
	@Override
	public void notifyChosenLeaderCard(String leaderCardName) {
		sendCommand(new ChosenLeaderCardCommand(leaderCardName, playerColor));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyMove(java.lang.String)
	 */
	@Override
	public void notifyMove(String move) {
		sendCommand(new PlayerMoveCommand(move));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyChosenPrivileges(java.lang.String)
	 */
	@Override
	public void notifyChosenPrivileges(String choices) {
		System.out.println("\nCLIENTCONTROLLER: before check and send\n");
		ArrayList<Integer> commandConstructor = parseString(choices);
		if (commandConstructor.size() != 0)
			sendCommand(new ChosenPrivilegeCommand(commandConstructor));
		else {
			userInterface.invalidInput();
			notifyInvalidInput();
		}
		System.out.println("\nCLIENTCONTROLLER: after check and send\n");
	}

	/**
	 * Sets the command handler.
	 *
	 * @param handler the new command handler
	 */
	public void setCommandHandler(ClientCommandHandler handler) {
		this.commandHandler = handler;
	}

	/**
	 * Sets the user interface.
	 *
	 * @param userInterface the new user interface
	 */
	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyInvalidInput()
	 */
	@Override
	public void notifyInvalidInput() {
		sendCommand(new InvalidInputCommand());
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyCouncilPalace(java.util.ArrayList)
	 */
	@Override
	public void notifyCouncilPalace(ArrayList<String> actionConstructor) {
		sendCommand(new PlaceIntoCouncilPalaceCommand(actionConstructor.get(0),
				Integer.parseInt(actionConstructor.get(1))));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyTakeCardAction(java.util.ArrayList)
	 */
	@Override
	public void notifyTakeCardAction(ArrayList<String> actionConstructor) {
		try {
			TakeCardCommand takeCardCommand = new TakeCardCommand(actionConstructor.get(0),
					Integer.parseInt(actionConstructor.get(4)), Integer.parseInt(actionConstructor.get(1)),
					CardType.values()[Integer.parseInt(actionConstructor.get(3)) - 1]);
			sendCommand(takeCardCommand);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("action constructor non riempito");
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyMarket(java.util.ArrayList)
	 */
	@Override
	public void notifyMarket(ArrayList<String> actionConstructor) {
		try {
			PlaceIntoMarketCommand placeIntoMarketCommand = new PlaceIntoMarketCommand(actionConstructor.get(0),
					actionConstructor.get(3), Integer.parseInt(actionConstructor.get(1)));
			sendCommand(placeIntoMarketCommand);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("action constructor non riempito");
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyHarvest(java.util.ArrayList)
	 */
	@Override
	public void notifyHarvest(ArrayList<String> actionConstructor) {
		try {
			HarvestCommand harvestCommand = new HarvestCommand(actionConstructor.get(0),
					Integer.parseInt(actionConstructor.get(1)), Integer.parseInt(actionConstructor.get(3)));
			sendCommand(harvestCommand);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("action constructor non riempito");
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyProduction(java.util.ArrayList)
	 */
	@Override
	public void notifyProduction(ArrayList<String> actionConstructor) {
		try {
			ProductionCommand productionCommand = new ProductionCommand(actionConstructor.get(0),
					Integer.parseInt(actionConstructor.get(1)), Integer.parseInt(actionConstructor.get(3)));
			sendCommand(productionCommand);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("action constructor non riempito");
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyDiscardedLeaderCard(java.lang.String)
	 */
	@Override
	public void notifyDiscardedLeaderCard(String discardedLeaderCard) {
		sendCommand(new DiscardLeaderCardCommand(discardedLeaderCard));

	}

	/**
	 * Sets the player color.
	 *
	 * @param color the new player color
	 */
	public void setPlayerColor(String color) {
		this.playerColor = color;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyCredentials(java.util.ArrayList)
	 */
	@Override
	public void notifyCredentials(ArrayList<String> actionConstructor) {
		sendCommand(new SendCredentialsCommand(actionConstructor.get(0), actionConstructor.get(1), playerColor));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyFinishRound()
	 */
	@Override
	public void notifyFinishRound() {
		sendCommand(new FinishRoundCommand());
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyExcommunicationEffectChoice(java.lang.Boolean)
	 */
	@Override
	public void notifyExcommunicationEffectChoice(Boolean choice) {
		sendCommand(new ChurchSupportCommand(playerColor, choice));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyProductionChoices(java.lang.String)
	 */
	@Override
	public void notifyProductionChoices(String choices) {
		ArrayList<Integer> commandConstructor = parseString(choices);
		if (commandConstructor.size() != 0)
			sendCommand(new ProductionActivationCommand(commandConstructor));
		else {
			userInterface.invalidInput();
			notifyInvalidInput();
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyProductionChoices(java.util.ArrayList)
	 */
	/* 
	 * Overload was needed for GUI
	 */
	@Override
	public void notifyProductionChoices(ArrayList<Integer> choices) {
		sendCommand(new ProductionActivationCommand(choices));
	}

	/**
	 * Parses the string.
	 *
	 * @param choices the choices
	 * @return the array list
	 */
	private ArrayList<Integer> parseString(String choices) {
		System.out.println("\n\n sono nella parseStirng" + choices + "\n\n");
		char[] charArray = choices.toCharArray();
		for (int i = 0; i < choices.length(); i++) {
			System.out.println("\n\n" + charArray[i] + "\n\n");
		}
		ArrayList<Integer> commandConstructor = new ArrayList<Integer>();
		for (int i = 0; i < charArray.length; i += 2) {
			if (Character.getNumericValue(charArray[i]) != -1)
				commandConstructor.add(Character.getNumericValue(charArray[i]));
			else {
				commandConstructor.clear(); // Se l'utente inserisce una stringa
											// a muzzo
				break; // il metodo ritorna una lista vuota
			}
		}
		return commandConstructor;
	}

	/**
	 * Notify leader effect activation.
	 *
	 * @param leaderCardName the leader card name
	 */
	public void notifyLeaderEffectActivation(String leaderCardName) {
		sendCommand(new ActivateLeaderCardCommand(leaderCardName, playerColor));

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.InputObserver#notifyChatMessage(java.lang.String)
	 */
	@Override
	public void notifyChatMessage(String message) {
		String m = "<--" + playerColor + "--> " + message;
		sendCommand(new ChatMessageClientCommand(m));
	}

	/**
	 * Notify request closure command.
	 */
	public void notifyRequestClosureCommand() {
		sendCommand(new RequestClosureCommand(playerColor));
	}

	public void notifyAuthenticationRequest(String username, String password) {
		sendCommand(new SendCredentialsCommand(username, password, playerColor));
		
	}

	public void notifySatanChoice(String playerColor) {
		sendCommand(new SatanChoiceCommand(playerColor));
	}

}