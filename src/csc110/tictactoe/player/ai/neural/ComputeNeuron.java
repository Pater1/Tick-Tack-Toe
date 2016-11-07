package csc110.tictactoe.player.ai.neural;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import csc110.wrappers.Vector2Int;

public class ComputeNeuron implements Neuron {
	private Vector2Int inA, inB;
	private Gate logic;
	
	public void SetVectors(Vector2Int a, Vector2Int b){
		inA = a;
		inB = b;
		Random rand = new Random();
		logic = Gate.values()[rand.nextInt(Gate.values().length)];
	}
	
	public int GetResult(Brain fromBrain){
		int result = 0;
		switch(logic){
			case add:
				result = fromBrain.GetNeuronResult(inA) + fromBrain.GetNeuronResult(inB);
			case subtract:
				result = fromBrain.GetNeuronResult(inA) - fromBrain.GetNeuronResult(inB);
		}
		if(result != 0){
			result /= Math.abs(result);
		}
		return result;
	}
	
	public ArrayList<Integer> Serialize(Vector2Int index){
		 ArrayList<Integer> list = new ArrayList<Integer>();

		 list.add(index.x);
		 list.add(index.y);
		 list.add(inA.x);
		 list.add(inA.y);
		 list.add(inB.x);
		 list.add(inB.y);
		 list.add(logic.ordinal());
		 list.add(-1000);
		 
		 return list;
	}
	public static ComputeNeuron Deserialize(List<Integer> list){
		ComputeNeuron cn = new ComputeNeuron(); 

		cn.inA = Vector2Int.newVector(list.get(2), list.get(3));
		cn.inB = Vector2Int.newVector(list.get(4), list.get(5));
		cn.logic = Gate.values()[list.get(6)];
		
		return cn;
	}
}
