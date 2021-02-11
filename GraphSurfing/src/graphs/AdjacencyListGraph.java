package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdjacencyListGraph<T> extends Graph<T> {

	Map<T,Vertex> keyToVertex;
	int numEdges;
	HashSet<T> visitedKeys;
	
	private class Vertex {
		T key;
		List<Vertex> successors;
		List<Vertex> predecessors;
		
		Vertex(T key) {
			this.key = key;
			this.successors = new ArrayList<Vertex>();
			this.predecessors = new ArrayList<Vertex>();
		}
		
		@Override
		public String toString(){
			return this.key.toString();
		}
		
	}
	
	AdjacencyListGraph(Set<T> keys) {
		this.keyToVertex = new HashMap<T,Vertex>();
		for (T key : keys) {
			Vertex v = new Vertex(key);
			this.keyToVertex.put(key, v);
		}
		this.visitedKeys = new HashSet<T>();
	}

	@Override
	public int size() {
		return keyToVertex.size();
	}

	@Override
	public int numEdges() {
		return this.numEdges;
	}

	@Override
	public boolean addEdge(T from, T to) {
		if(!this.keyToVertex.containsKey(from) || !this.keyToVertex.containsKey(to)){
			throw new NoSuchElementException();
		}
		Vertex toCheck = this.keyToVertex.get(from);
		Vertex other = this.keyToVertex.get(to);
		
		if(toCheck.successors.contains(other) || other.predecessors.contains(toCheck)){
			return false;
		}
		
		this.keyToVertex.get(from).successors.add(other);
		this.keyToVertex.get(to).predecessors.add(toCheck);
		
		this.numEdges++;
		return true;
	}

	@Override
	public boolean hasVertex(T key) {
		return keyToVertex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if(!this.keyToVertex.containsKey(from) || !this.keyToVertex.containsKey(to)){
			throw new NoSuchElementException();
		}
		
		Vertex start = this.keyToVertex.get(from);
		Vertex end = this.keyToVertex.get(to);
		if(start.successors.contains(end)) return true;
		
		return false;
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		if(!this.keyToVertex.containsKey(from) || !this.keyToVertex.containsKey(to)){
			throw new NoSuchElementException();
		}
		if(!this.hasEdge(from, to)) return false;
		
		Vertex start = this.keyToVertex.get(from);
		Vertex end = this.keyToVertex.get(to);
		
		start.successors.remove(end);
		end.predecessors.remove(start);
		
		return true;
	}

	@Override
	public int outDegree(T key) {
		if(!this.keyToVertex.containsKey(key)) throw new NoSuchElementException();
		return this.keyToVertex.get(key).successors.size();
	}

	@Override
	public int inDegree(T key) {
		if(!this.keyToVertex.containsKey(key)) throw new NoSuchElementException();
		return this.keyToVertex.get(key).predecessors.size();
	}

	@Override
	public Set<T> keySet() {
		return keyToVertex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		if(!keyToVertex.containsKey(key)) throw new NoSuchElementException();
		HashSet<T> retSet = new HashSet<T>();
		Vertex head = this.keyToVertex.get(key);
		
		for(Vertex v : head.successors){
			retSet.add(v.key);
		}
		return retSet;
	}

	@Override
	public Set<T> predecessorSet(T key) {
		if(!keyToVertex.containsKey(key)) throw new NoSuchElementException();
		HashSet<T> retSet = new HashSet<T>();
		Vertex head = this.keyToVertex.get(key);
		
		for(Vertex v : head.predecessors){
			retSet.add(v.key);
		}
		return retSet;
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		if(!this.keyToVertex.containsKey(key)) throw new NoSuchElementException();
		Vertex v = this.keyToVertex.get(key);
		return new GraphIter(v.successors, v.successors.size());
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		if(!this.keyToVertex.containsKey(key)) throw new NoSuchElementException();
		Vertex v = this.keyToVertex.get(key);
		return new GraphIter(v.predecessors, v.predecessors.size());
	}
	
	public class GraphIter implements Iterator<T> {
		List<Vertex> list;
		int startSize;
		int current;
		
		public GraphIter(List<Vertex> successors, int size) {
			this.list = successors;
			this.startSize = size;
			this.current=0;
		}

		@Override
		public boolean hasNext() {
			if(current<startSize) return true;
			return false;
		}

		@Override
		public T next() {
			this.current++;
			return list.get(current-1).key;
		}
	}

//	@Override
//	public Set<T> stronglyConnectedComponent(T key) {
//		
//		
//		
//		return null;
//	}

//	@Override
//	public List<T> shortestPath(T startLabel, T endLabel) {
//		//if start has edge to end, return a list of start to end
//		//return shortestPath of every start's successors?	recurse?
//		if(!this.keyToVertex.containsKey(startLabel) || !this.keyToVertex.containsKey(endLabel)){
//			throw new NoSuchElementException();
//		}
//		
//		this.visitedKeys = new HashSet<>();
//		Vertex start = this.keyToVertex.get(startLabel);
//		Vertex end = this.keyToVertex.get(endLabel);
//		ArrayList<T> retList = new ArrayList<T>();
//		
//		retList.add(startLabel);
//		if(start.successors.contains(end)){
//			retList.add(endLabel);
//			return retList;
//		}
//		
//		Vertex current = this.highestDegreeSuccessor(start);
//		
//		while(current.successors.size()!=0){
//			retList.add(current.key);
//			if(current.successors.contains(end)){
//				retList.add(endLabel);
//				return retList;
//			}
//			
//			if(this.visitedKeys.contains(current.key)){
//				return null;
//			}
//			current = this.highestDegreeSuccessor(current);
//		}
//		
//		if(current.key.equals(endLabel)){
//			retList.add(endLabel);
//			return retList;
//		}
//		
//		return null;
//	}
//	
//	/**
//	 * Finds the successor of "base" with the highest inDegree
//	 *
//	 * @param base "parent" of successors to check
//	 * @return successor with highest degree
//	 */
//	public Vertex highestDegreeSuccessor(Vertex base){
//		this.visitedKeys.add(base.key);
//		if(base.successors.size()<=0) return null;
//		int topCount=0;
//		Vertex topKey = base.successors.get(0);
//		
//		for(Vertex v : base.successors){
//			int current = v.successors.size();
//			if(current > topCount){
//				topCount=current;
//				topKey = v;
//			}
//		}
//		return topKey;
//	}
//	
//	public Vertex lowestDegreeSuccessor(Vertex base){
//		this.visitedKeys.add(base.key);
//		if(base.successors.size()<=0) return null;
//		int topCount=Integer.MAX_VALUE;
//		Vertex bottomKey = base.successors.get(0);
//		
//		for(Vertex v : base.successors){
//			int current = v.successors.size();
//			if(current < topCount){
//				topCount=current;
//				bottomKey = v;
//			}
//		}
//		return bottomKey;
//	}

}