public class ChangeItemVersBin {

    public boolean changeItemToBin(DataSet dataset, Items item, Bin binDestination){

        for(int i=0; i<dataset.getListBins().size(); i++){
            if(dataset.getListBins().get(i).getListItem().contains(item)==true){
                dataset.getListBins().get(i).removeItem(item);
            }
        }
        if(binDestination.getTailleBinRestant()>=item.getTailleItem()){
            binDestination.addItem(item);
            return true;
        }else{
            return false;
        }
    }
}
