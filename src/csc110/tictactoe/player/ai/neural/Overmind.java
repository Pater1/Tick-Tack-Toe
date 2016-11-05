package csc110.tictactoe.player.ai.neural;

import java.util.ArrayList;
import java.util.Random;
import csc110.wrappers.Vector2Int;

public class Overmind {
			//fitness sorted	//list of brains	//serialized brains
	private ArrayList<			ArrayList<			ArrayList<Integer>>> brains = new ArrayList<ArrayList<ArrayList<Integer>>>();
	
	public enum Difficulty{
		easy,
		medium,
		hard
	}
	public Difficulty difficulty = Difficulty.easy;
	
	public Vector2Int brainSize = Vector2Int.newVector(20, 20);
	
	public Brain GetNextBrain(){
		if(brains.size() <= 0){
			brains.add(new ArrayList<ArrayList<Integer>>());
			brains.add(new ArrayList<ArrayList<Integer>>());
			brains.add(new ArrayList<ArrayList<Integer>>());
		}
		Brain br = BreedabilityCatch(GetBreedingBrainsFromPool());
		ReturnBrain(br.Serialize());
		return br;
	}

	private void ReturnBrain(ArrayList<Integer> serialize) {
		switch(serialize.get(0)){
		case -1:
			brains.get(0).add(serialize);
			break;
		case 0:
			brains.get(1).add(serialize);
			break;
		case 1:
			brains.get(2).add(serialize);
			break;
		default:
			throw new IllegalArgumentException("All fitnesses must be normalized to 1, -1, or 0.");
		}
	}

	private ArrayList<ArrayList<Integer>> GetBreedingBrainsFromPool(){
		ArrayList<ArrayList<Integer>> br = new ArrayList<ArrayList<Integer>>();
		
		switch(difficulty){
			case hard:
				br.addAll(brains.get(2));
			case medium:
				br.addAll(brains.get(1));
			case easy:
				br.addAll(brains.get(0));
		}
		
		return br;
	}
	
	private Brain BreedabilityCatch(ArrayList<ArrayList<Integer>> br){
		if(br.size() <= 1){
			return Brain.RandomBrain(brainSize);
		}else{
			Random rand = new Random();
			return BreedBrains(br.get(rand.nextInt(br.size())), br.get(rand.nextInt(br.size())));
		}
	}
	
	private Brain BreedBrains(ArrayList<Integer> ain1, ArrayList<Integer> ain2){
		Random rand = new Random();
		ArrayList<Integer> ou = new ArrayList<Integer>(), rbr = Brain.RandomBrain(brainSize).Serialize();
		for(int i = 0; i < ain1.size(); i++){
			int r = rand.nextInt(130);
			if(r < 50){
				ou.add(ain1.get(i));
			}else if(r >= 98){
				ou.add(rbr.get(i));
			}else{
				ou.add(ain2.get(i));
			}
		}
		
		return Brain.Deserialize(ou);
	}
}
