package csc110.tictactoe.player.ai.neural;

import java.util.ArrayList;

import csc110.wrappers.Vector2Int;

public interface Neuron {
	public int GetResult(Brain fromBrain);
	public void SetVectors(Vector2Int a, Vector2Int b);
	public ArrayList<Integer> Serialize(Vector2Int index);
}