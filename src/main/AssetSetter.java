package main;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
    }

    public void setAnts(int n){
        for (int i = 0; i < n; i++){
            gp.ants[i] = new Ant(gp);
            gp.ants[i].ant_x = gp.gp_size * 12;
            gp.ants[i].ant_y = gp.gp_size * 7;
        }
    }
}
