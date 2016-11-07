package csc110.tictactoe.player.ai.neural;

import java.util.ArrayList;
import java.util.Random;

import ttt.*;
import csc110.wrappers.Vector2Int;

public class Brain {
	private int[] inputs = new int[9];
	
	private int width = 20, depth = 20, fitness;
	private ComputeNeuron outputX = new ComputeNeuron(), outputY = new ComputeNeuron();
	private ComputeNeuron[][] brainMap = null;
	
	public void SetFitness(int fit){
		fitness = fit;
	}
	
 	public ArrayList<Integer> Serialize(){
		ArrayList<Integer> output = new ArrayList<Integer>();

		output.add(fitness);
		output.add(brainMap.length);
		output.add(brainMap[0].length);
		
		output.addAll(outputX.Serialize(Vector2Int.newVector(-2, 0)));
		output.addAll(outputY.Serialize(Vector2Int.newVector(-2, 1)));
		
		for(int i = 0; i < brainMap.length; i++){
			for(int j = 0; j < brainMap[i].length; j++){
				Vector2Int v2i = Vector2Int.newVector(i, j);
				ComputeNeuron cn = brainMap[i][j];
				ArrayList<Integer> ali = cn.Serialize(v2i);
				output.addAll(ali);
			}
		}
		
		return output;
	}
	public static Brain Deserialize(ArrayList<Integer> ou) {
		Brain br = new Brain();
		
		br.fitness = 0;
		br.width = ou.get(1);
		br.depth = ou.get(2);

		br.outputX = ComputeNeuron.Deserialize(ou.subList(3, 10));
		br.outputY = ComputeNeuron.Deserialize(ou.subList(11, 18));
		
		br.brainMap = new ComputeNeuron[br.width][br.depth];
		for(int i = 19; i < ou.size(); i += 8){
			ComputeNeuron cn = ComputeNeuron.Deserialize(ou.subList(i, i+8));
			int x = ou.get(i);
			int y = ou.get(i+1);
			br.brainMap[x][y] = cn;
		}
		
		return br;
	}
	
	public static Brain RandomBrain(Vector2Int size){
		Brain br = new Brain();
		br.fitness = -1;
		br.brainMap = new ComputeNeuron[size.x][size.y];
		Random rand = new Random();
		for(int i = br.brainMap.length-1; i >= 0; i--){
			for(int j = br.brainMap[i].length-1; j >= 0; j--){
				br.brainMap[i][j] = new ComputeNeuron();
				
				br.brainMap[i][j].SetVectors(
										Vector2Int.newVector(((j==0)?rand.nextInt(br.inputs.length-1):rand.nextInt(br.brainMap.length-1)), j-1)
										,Vector2Int.newVector(((j==0)?rand.nextInt(br.inputs.length-1):rand.nextInt(br.brainMap.length-1)), j-1));
			}
		}
		br.outputX.SetVectors(Vector2Int.newVector(rand.nextInt(br.brainMap.length), br.brainMap[0].length-1)
				,Vector2Int.newVector(rand.nextInt(br.brainMap.length), br.brainMap[0].length-1));
		br.outputY.SetVectors(Vector2Int.newVector(rand.nextInt(br.brainMap.length), br.brainMap[0].length-1)
				,Vector2Int.newVector(rand.nextInt(br.brainMap.length), br.brainMap[0].length-1));
		
		return br;
	}
	
	public int GetNeuronResult(Vector2Int index) {
		if(index.y < 0){
			return inputs[index.x];
		}
		return brainMap[index.x][index.y].GetResult(this);
	}
	
	public int[] GetOutputs(){
		int[] outs = new int[2];
		outs[0] = outputX.GetResult(this);
		outs[1] = outputY.GetResult(this);
		
		return outs;
	}
	public void UpdateInputs(Board gamePlay) {
		for(int i = 0; i < gamePlay.getBase().length; i++){
			for(int j = 0; j < gamePlay.getBase()[i].length; j++){
				int index = i*3 + j;
				inputs[index] = gamePlay.getBase()[i][j];
			}
		}
	}
}
