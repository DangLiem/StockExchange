/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchange;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 *
 * @author dangliem
 */
public class SellOrder {
    private PriorityQueue<StockOrder> pq_UET, pq_EPU, pq_MTA;
    private HashMap<String, PriorityQueue<StockOrder>> treeSellOrder;
 

    public PriorityQueue<StockOrder> getPq_UET() {
        return pq_UET;
    }

    public void setPq_UET(PriorityQueue<StockOrder> pq_UET) {
        this.pq_UET = pq_UET;
    }

    public PriorityQueue<StockOrder> getPq_EPU() {
        return pq_EPU;
    }

    public void setPq_EPU(PriorityQueue<StockOrder> pq_EPU) {
        this.pq_EPU = pq_EPU;
    }

    public PriorityQueue<StockOrder> getPq_MTA() {
        return pq_MTA;
    }

    public void setPq_MTA(PriorityQueue<StockOrder> pq_MTA) {
        this.pq_MTA = pq_MTA;
    }
    

    public SellOrder() {
        pq_UET = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return x.getPrice() - y.getPrice();
        });
        pq_EPU = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return x.getPrice() - y.getPrice();
        });
        pq_MTA = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return x.getPrice() - y.getPrice();
        });
        treeSellOrder = new HashMap<>();
        treeSellOrder.put("UET", pq_UET);
        treeSellOrder.put("EPU", pq_EPU);
        treeSellOrder.put("MTA", pq_MTA);
    }
    public HashMap<String, PriorityQueue<StockOrder>> getTreeSellOrder() {
        return treeSellOrder;
    }

    public void setTreeSuyOrder(HashMap<String, PriorityQueue<StockOrder>> sellOrder) {
        this.treeSellOrder = sellOrder;
    }
    public void add(String catStock, int userId, int total, int price){
        if(catStock.equals("UET"))
            pq_UET.add(new StockOrder(userId, total, price));
        else if(catStock.equals("EPU"))
            pq_EPU.add(new StockOrder(userId, total, price));
        else
            pq_MTA.add(new StockOrder(userId, total, price));       
    }
  
    public void show(){
     
        for(Map.Entry<String, PriorityQueue<StockOrder>> entry:treeSellOrder.entrySet()){
            System.out.println(entry.getKey());
            PriorityQueue<StockOrder> val = clone(entry.getValue());
            while(!val.isEmpty()){
                System.out.println(val.remove());
            }
          
        }
    }
    
    public static PriorityQueue<StockOrder> clone(PriorityQueue<StockOrder> input){
        PriorityQueue<StockOrder> result = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return x.getPrice() - y.getPrice();
        });
        Iterator<StockOrder> itr = input.iterator();
        while(itr.hasNext()){
            result.add(itr.next());
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("SellOrder:");
        SellOrder so = new SellOrder();
        so.add("UET", 1, 20, 100);
        so.add("UET", 2, 30, 500);
        so.add("MTA", 1, 30, 200);
        so.add("UET", 2, 30, 150);
        so.add("MTA", 3, 40, 250);
        so.add("EPU", 2, 30, 50);
        so.add("EPU", 2, 30, 70);

        so.show();
      
    }
}
