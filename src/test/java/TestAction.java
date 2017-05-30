import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.Servant;

public class TestAction {

	private Board board;
	private Player mirko;
	private Action takeCardAction;
	
	@Before
	public void setUp(){
		try {
			board = new Board();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mirko = new Player("Mirko","Nero");
		mirko.getResourceChest().addChest(new ResourceChest(100,100,100,100,100,100,100));
		
		Dice.ORANGE_DICE.getRandomFaceValue();
		Dice.WHITE_DICE.getRandomFaceValue();
		Dice.BLACK_DICE.getRandomFaceValue();
		
		takeCardAction=new TakeCardAction(mirko.getFamilyMembers().get(Color.ORANGE),
				board.getTower(CardType.TERRITORY).getFloors().get(1),new Servant(0));
		
		try {
			takeCardAction.apply();
		} catch (NotApplicableException e) {
			System.out.println("Not applicable");
			e.printStackTrace();
		}
		
		System.out.println(mirko.getDeckOfType(CardType.TERRITORY).get(0).toString());
		System.out.println(mirko.getResourceChest().toString());
	}
	
	
	@Test
	public void testTakeCardAction(){
//		assertEquals(, );
	}

}
