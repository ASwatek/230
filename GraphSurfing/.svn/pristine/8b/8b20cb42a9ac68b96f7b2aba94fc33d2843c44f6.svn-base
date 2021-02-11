package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Abstract class to represent the Graph ADT. It is assumed that every vertex contains some 
 * data of type T, which serves as the identity of that node and provides access to it.
 * 
 * @author Nate Chenette
 *
 * @param <T>
 */
public abstract class Graph<T> {
	
	/**
	 * Returns the number of vertices in the graph.
	 * @return
	 */
	public abstract int size();
	

	/**
	 * Returns the number of edges in the graph.
	 * @return
	 */
	public abstract int numEdges();

	
	/**
	 * Adds a directed edge from the vertex containing "from" to the vertex containing "to". 
	 * @param from
	 * @param to
	 * @return true if the add is successful, false if the edge is already in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean addEdge(T from, T to);

	
	/**
	 * Determines whether a vertex is in the graph.
	 * @param key
	 * @return true if the graph has a vertex containing key.
	 */
	public abstract boolean hasVertex(T key);
	
	
	/**
	 * Determines whether an edge is in the graph.
	 * @param from
	 * @param to
	 * @return true if the directed edge (from, to) is in the graph, otherwise false.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean hasEdge(T from, T to) throws NoSuchElementException;
	
	
	/**
	 * Removes an edge from the graph.
	 * @param from
	 * @param to
	 * @return true if the remove is successful, false if the edge is not in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean removeEdge(T from, T to) throws NoSuchElementException;
	
	/**
	 * Computes out-degree of a vertex.
	 * @param key
	 * @return the number of successors of the vertex containing key
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract int outDegree(T key) throws NoSuchElementException;

	
	/**
	 * Computes in-degree of a vertex.
	 * @param key
	 * @return the number of predecessors of the vertex containing key
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract int inDegree(T key) throws NoSuchElementException;
	
	
	/**
	 * Returns the Set of vertex keys in the graph. 
	 * @return
	 */
	public abstract Set<T> keySet();
	
	/**
	 * Returns a Set of keys that are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Set<T> successorSet(T key) throws NoSuchElementException;
	
	/**
	 * Returns a Set of keys that are predecessors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Set<T> predecessorSet(T key) throws NoSuchElementException;
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> successorIterator(T key) throws NoSuchElementException;
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> predecessorIterator(T key) throws NoSuchElementException;
	
	/**
	 * Finds the strongly-connected component of the provided key.
	 * @param key
	 * @return a set containing all data in the strongly connected component of the vertex
	 * containing key 
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> stronglyConnectedComponent(T key) throws NoSuchElementException{
		if(!this.keySet().contains(key)) throw new NoSuchElementException();
		HashSet<T> ret = new HashSet<>();
		
		Set<T> succSet = this.successorSet(key);
		Set<T> predSet = this.predecessorSet(key);
		if(succSet.isEmpty() || predSet.isEmpty()){//nothing connects if either is the case
			ret.add(key);
			return ret;
		}
		/**
		 * queue of "working nodes" from breadth first search
		 * set of nodes that are reachable from start (by using successors)
		 * set of nodes that can reach the start (by using predecessors)
		 * hashset of nodes that have been visited to prevent bad stuff
		 *		a node is considered visited once it enters the queue
		 * for each node in reachable, if canReach contains, add to retSet
		 */
		
		HashSet<T> reachableFromKey = new HashSet<>();
		HashSet<T> canReachKey = new HashSet<>();
		HashSet<T> backKeys = new HashSet<>();
		HashSet<T> frontKeys = new HashSet<>();
		Queue<T> workingSet = new PriorityQueue<>();
		workingSet.offer(key);
		T current;
		
		while(!workingSet.isEmpty()){
			current = workingSet.poll();
			for(T pred : this.predecessorSet(current)){
				if (!backKeys.contains(pred)) {
					canReachKey.add(pred);
					backKeys.add(pred);
					workingSet.offer(pred);
				}
			}
		}
		workingSet.clear();
		workingSet.offer(key);
		while(!workingSet.isEmpty()){
			current = workingSet.poll();
			for (T succ : this.successorSet(current)) {
				if (!frontKeys.contains(succ)) {
					reachableFromKey.add(succ);
					frontKeys.add(succ);
					workingSet.offer(succ);
				}
			}
		}
		
//		while(!workingSet.isEmpty()){
//			current = workingSet.poll();
//			for(T pred : this.predecessorSet(current)){
//				if(!backKeys.contains(pred)){					
//					canReachKey.add(pred);
//					backKeys.add(pred);
//					workingSet.offer(pred);
//				}
//			}
//			for(T succ : this.successorSet(current)){
//				if(!frontKeys.contains(succ)){
//					reachableFromKey.add(succ);
//					frontKeys.add(succ);
//					workingSet.offer(succ);
//				}
//			}
//		}
		
		for(T guy : canReachKey){
			if(reachableFromKey.contains(guy)){
				ret.add(guy);
			}
		}
		
		return ret;
		
		
		//public Container(int size, T previous, T current, T successor){
//		Container start = new Container(1, null, key, null);
//		Stack<Container> order = new Stack<>();
//		order.push(start);
//		HashSet<T> neverVisit = new HashSet<>();//nodes with no successors, so they arent possible
//		
//		while(!order.isEmpty()){//needs to prevent repetition	
//			Container current = order.pop();
//			Set<T> currentSet = this.successorSet(current.self);
//			//currentKey : (otherNode : hasVisited)
////			HashMap<T, HashMap<T, Boolean>> hasVisited = new HashMap<>();
////			HashMap<T, HashSet<BooleanContainer>> hasVisited = new HashMap<>();	
//			HashMap<T, HashSet<T>> hasVisited = new HashMap<>();
//			if(currentSet.isEmpty()){
//				neverVisit.add(current.self);
//			}else{
//				for(T succKey : currentSet){
//					if(this.successorSet(succKey).isEmpty()){
//						neverVisit.add(succKey);
//					}
//					if(!neverVisit.contains(current.self) && !hasVisited.get(current.self).contains(succKey)){
//						Container toAdd = new Container(current.size+1, current.self, succKey, null);
//						order.push(toAdd);
//						hasVisited.get(current.self).add(succKey);
//					}
//				}				
//			}
//			
//			
//		}
//		return ret;
		
		
		
		
		
		
//		HashSet<T> ret = new HashSet<T>();
//		ret.add(key);
//		Set<T> startSet = this.successorSet(key);
//		Set<T> predSet = this.predecessorSet(key);
//		
//		if(startSet.isEmpty() || predSet.isEmpty()){//nothing connects if either is the case
//			return ret;
//		}
//		
//		List<T> retGuy = new ArrayList<>();
//		int max = Integer.MIN_VALUE;
//		
//		for(T item : predSet){
//			List<T> temp = this.shortestPath(key, item);
//			if(temp!=null){			
//				if(temp.size()>max){
//					max = temp.size();
//					retGuy = temp;
//				}
//				System.out.println(temp.toString());
//			}
//		}
//		
//		HashSet<T> realRet = new HashSet<T>();
//		realRet.addAll(retGuy);
//		return realRet;
		
		
		
//		Wrapper startPoint = new Wrapper(0, null, startLabel);
//		paths.put(startLabel, startPoint);
//		
//		PriorityQueue orderToFollow = new PriorityQueue<Wrapper>();
//		orderToFollow.add(startPoint);
//		
//		while(!orderToFollow.isEmpty()){
//			Wrapper current = (Graph<T>.Wrapper) orderToFollow.poll();
//			Set<T> currentSuccessors = this.successorSet(current.self);
//			
//			for(T successor : currentSuccessors){
//				if(paths.containsKey(successor)){//current.self
//					Wrapper toCheck = paths.get(successor);//current.self
//					if(toCheck.distanceFromStart > current.distanceFromStart+1){
//						Wrapper toAdd = new Wrapper(current.distanceFromStart+1, current.self, successor);
//						paths.put(successor, toAdd);
//					}
//				}else{
//					Wrapper toAdd = new Wrapper(current.distanceFromStart+1, current.self, successor);
//					paths.put(successor, toAdd);
//					orderToFollow.add(toAdd);
//				}
//			}
//		}
		
//		for(T successor : startSet){
//			Set<T> newSet = this.successorSet(successor);
//			ret.add(successor);
//			for(T succ2 : newSet){
//				ret.add(succ2);
//				if(this.hasEdge(succ2, key)){
//					return ret;
//				}
//			}
//		}
//		
//		return ret;
	}

	public class BooleanContainer{
		boolean visited;
		T node;
		
		public BooleanContainer(boolean visited, T node){
			this.visited = visited;
			this.node = node;
		}
	}
	
	public class Container{
		int size;
		T self;
		T previous;
		T successor;
		
		public Container(int size, T previous, T current, T successor){
			this.size = size;
			this.previous = previous;
			this.self = current;
			this.successor = successor;
		}
		
		@Override
		public String toString(){//used for debugger view
			return size + ", " + previous + ", " + self;
		}
	}
	
	/**
	 * Searches for the shortest path between start and end points in the graph.
	 * @param start
	 * @param end
	 * @return a list of data, starting with start and ending with end, that gives the path through
	 * the graph, or null if no such path is found.  
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public List<T> shortestPath(T startLabel, T endLabel) throws NoSuchElementException{
		if(!this.keySet().contains(startLabel) || !this.keySet().contains(endLabel)){
			throw new NoSuchElementException();
		}
		ArrayList<T> ret = new ArrayList<>();
		HashMap<T, Wrapper> paths = new HashMap<>();//T : list from start to this path
		int lowest = -1;
		int distanceFromStart=0;
		T predecessor = null;
		ret.add(startLabel);
		
		if(this.hasEdge(startLabel, endLabel)){
			ret.add(endLabel);
			return ret;
		}
		
		Wrapper startPoint = new Wrapper(0, null, startLabel);
		paths.put(startLabel, startPoint);
		
		PriorityQueue orderToFollow = new PriorityQueue<Wrapper>();
		orderToFollow.add(startPoint);
		
		while(!orderToFollow.isEmpty()){
			Wrapper current = (Graph<T>.Wrapper) orderToFollow.poll();
			Set<T> currentSuccessors = this.successorSet(current.self);
			
			for(T successor : currentSuccessors){
				if(paths.containsKey(successor)){//current.self
					Wrapper toCheck = paths.get(successor);//current.self
					if(toCheck.distanceFromStart > current.distanceFromStart+1){
						Wrapper toAdd = new Wrapper(current.distanceFromStart+1, current.self, successor);
						paths.put(successor, toAdd);
					}
				}else{
					Wrapper toAdd = new Wrapper(current.distanceFromStart+1, current.self, successor);
					paths.put(successor, toAdd);
					orderToFollow.add(toAdd);
				}
			}
		}
		
//		for(Wrapper p : paths.values()){
//			p.
//			
//		}
		
		Wrapper oper = paths.get(endLabel);
		if(oper==null) return null;
		while(oper.predecessor!=null){
			ret.add(1, oper.self);
			oper = paths.get(oper.predecessor);
		}
		if(!ret.get(ret.size()-1).equals(endLabel)){
			return null;
		}
		
		return ret;
		
//		Iterator iterator = this.successorIterator(startLabel);
//		while(iterator.hasNext()){
//			T current = (T) iterator.next();
//			Set<T> setToCheck = this.successorSet(current);
//			
//			for(T item : setToCheck){
//				
//			}
//			
//			distanceFromStart+=1;
//		}
//		if(lowest==-1){
//			return null;
//		}
//		return ret;
	}
	
	public class Wrapper implements Comparable<Wrapper>{
		int distanceFromStart;
		T predecessor;
		T self;
		
		public Wrapper(int distanceFromStart, T predecessor, T self){
			this.distanceFromStart=distanceFromStart;
			this.predecessor=predecessor;
			this.self=self;
		}

		@Override
		public int compareTo(Wrapper other) {
			return this.distanceFromStart-other.distanceFromStart;
		}
		
		@Override
		public String toString(){//used for debugger view
			return this.distanceFromStart + ", " + this.predecessor.toString() + ", " + this.self.toString();
		}
		
	}
}