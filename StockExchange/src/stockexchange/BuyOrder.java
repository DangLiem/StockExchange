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
public class BuyOrder {
    private PriorityQueue<StockOrder> pq_UET, pq_EPU, pq_MTA;
    private HashMap<String, PriorityQueue<StockOrder>> treeBuyOrder;

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
    

    public BuyOrder() {
        pq_UET = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return y.getPrice() - x.getPrice();
        });
        pq_EPU = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return y.getPrice() - y.getPrice();
        });
        pq_MTA = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return y.getPrice() - x.getPrice();
        });
        treeBuyOrder = new HashMap<>();
        treeBuyOrder.put("UET", pq_UET);
        treeBuyOrder.put("EPU", pq_EPU);
        treeBuyOrder.put("MTA", pq_MTA);
    }
    public HashMap<String, PriorityQueue<StockOrder>> getTreeBuyOrder() {
        return treeBuyOrder;
    }

    public void setTreeBuyOrder(HashMap<String, PriorityQueue<StockOrder>> buyOrder) {
        this.treeBuyOrder = buyOrder;
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
     
        for(Map.Entry<String, PriorityQueue<StockOrder>> entry:treeBuyOrder.entrySet()){
            System.out.println(entry.getKey());
            PriorityQueue<StockOrder> val = clone(entry.getValue());
            while(!val.isEmpty()){
                System.out.println(val.remove());
            }
          
        }
    }
    
    public static PriorityQueue<StockOrder> clone(PriorityQueue<StockOrder> input){
        PriorityQueue<StockOrder> result = new PriorityQueue<>((StockOrder x, StockOrder y)->{
            return y.getPrice() - x.getPrice();
        });
        Iterator<StockOrder> itr = input.iterator();
        while(itr.hasNext()){
            result.add(itr.next());
        }
        return result;
    }
    
    
    public static void accept(String cat ,BuyOrder od, SellOrder so){
        // 2 truong hop xay ra dua vao phan tu dau tien cua hang doi mua
        // 1. gia mua >= gia ban khop lenh
                // 1.1 mua du - > remove ca 2
                // 1.2 mua it hon -> remove mua; tru ben ban
                // 1.3 mua nhieu hon -> kiem tra nguoi ban tiep theo, quay tro lai buoc dau
        // 2. gia mua < gia ban khong thuc hien
        
        //code thu voi ma UET
        StockOrder buy = od.getTreeBuyOrder().get(cat).peek();
        StockOrder sell = so.getTreeSellOrder().get(cat).peek();
        do{
            buy = od.getTreeBuyOrder().get(cat).peek();
            sell = so.getTreeSellOrder().get(cat).peek();
            if(buy.getPrice() >= sell.getPrice()){
                if(buy.getTotal().equals(sell.getTotal())){
                     od.getTreeBuyOrder().get(cat).remove();
                     so.getTreeSellOrder().get(cat).remove();
                }else if(buy.getTotal() < sell.getTotal()){
                    StockOrder tempSell = so.getTreeSellOrder().get(cat).peek();
                    tempSell.setTotal(sell.getTotal() - buy.getTotal());
                    so.getTreeSellOrder().get(cat).remove();
                    so.getTreeSellOrder().get(cat).add(tempSell);
                    od.getTreeBuyOrder().get(cat).remove();
                }else{
                    StockOrder tempBuy = od.getTreeBuyOrder().get(cat).peek();
                    tempBuy.setTotal(buy.getTotal() - sell.getTotal());
                    od.getTreeBuyOrder().get(cat).remove();
                    od.getTreeBuyOrder().get(cat).add(tempBuy);
                    so.getTreeSellOrder().get(cat).remove();
                }
            }
        }while((buy != null && sell !=null) && (buy.getPrice() >= sell.getPrice()));
    }
    
    public static void main(String[] args) {
        BuyOrder bo = new BuyOrder();
        bo.add("UET", 1, 20, 600);
        bo.add("UET", 2, 30, 500);
        bo.add("UET", 3, 20, 250);
        
        // 500, 250, 100
        
        SellOrder so = new SellOrder();
        so.add("UET", 5, 60, 400);
        so.add("UET", 1, 29, 300);
        so.add("UET",6, 60, 500);
        // 300, 400, 500
        System.out.println("Sell");
        so.show();
        System.out.println("Buy");
        bo.show();
        System.out.println("===============================");
        
        
        accept("UET", bo, so);
        
        
        System.out.println("Sell");
        so.show();
        System.out.println("Buy");
        bo.show();
        
    }
}
