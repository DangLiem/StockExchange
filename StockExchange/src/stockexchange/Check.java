/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchange;

/**
 *
 * @author dangliem
 */
public class Check {
    //
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
}
