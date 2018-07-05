package com.hekotech.ucangus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.sun.corba.se.impl.oa.toa.TOA;

import java.util.Random;

public class ucanGus extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture gus;
	Texture bee1,bee2,bee3;
	float gusx,gusy,b1y,b2y,b3y;
	int nbees=4;
	float [] bx = new float[nbees];
    float [][] by = new float[4][nbees];
	float distance = 0;
	float gravity= (float) 2.0;
    float velocity = (float) 0.0;


	int gamestate = 0;
	Circle gus_;
	Circle [] bee1_;
	Circle [] bee2_;
	Circle [] bee3_;

    ShapeRenderer sr ;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("full-background.png");
		bee1 = new Texture("sprite1.png");
		bee2 = new Texture("sprite2.png");
		bee3 = new Texture("sprite1.png");

        sr = new ShapeRenderer();
		gus = new Texture("frame-1.png");

		gus_ = new Circle();
		bee1_ = new Circle[nbees];
		bee2_ = new Circle[nbees];
		bee3_ = new Circle[nbees];


        gusx=Gdx.graphics.getWidth()/4;
        gusy=Gdx.graphics.getHeight()/3;

        distance=Gdx.graphics.getWidth()/2;
        for (int i = 0;i<nbees;i++){
            Random r1 = new Random();
            by[1][i] = (float)r1.nextInt(Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100) + Gdx.graphics.getHeight()/100;
            Random r2 = new Random();
            by[2][i] = (float)r2.nextInt(Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100) + Gdx.graphics.getHeight()/100;
            Random r3 = new Random();
            by[3][i] = (float)r3.nextInt(Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100) + Gdx.graphics.getHeight()/100;

            bx[i]=Gdx.graphics.getWidth()-bee1.getWidth()/2+i*distance;
            bee3_[i]=new Circle();
            bee2_[i]=new Circle();
            bee1_[i]=new Circle();

        }


	}

	@Override
	public void render () {
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(gamestate == 1){
            for(int i=0;i<nbees;i++){
                if(bx[i]<Gdx.graphics.getWidth()/15){
                    bx[i]+=nbees*distance;
                    Random r1 = new Random();
                    by[1][i] = (float)r1.nextInt(Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100) + Gdx.graphics.getHeight()/100;
                    Random r2 = new Random();
                    by[2][i] = (float)r2.nextInt(Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100) + Gdx.graphics.getHeight()/100;
                    Random r3 = new Random();
                    by[3][i] = (float)r3.nextInt(Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100) + Gdx.graphics.getHeight()/100;

                }else {

                    bx[i]-=5.0;
                }

                batch.draw(bee1,bx[i],by[1][i],Gdx.graphics.getWidth()/13,Gdx.graphics.getHeight()/11);
                batch.draw(bee2,bx[i],by[2][i],Gdx.graphics.getWidth()/13,Gdx.graphics.getHeight()/11);
                batch.draw(bee3,bx[i],by[3][i],Gdx.graphics.getWidth()/13,Gdx.graphics.getHeight()/11);

                bee1_[i] = new Circle(bx[i]+Gdx.graphics.getWidth()/26,by[1][i]+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/30);
                bee2_[i] = new Circle(bx[i]+Gdx.graphics.getWidth()/26,by[2][i]+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/30);
                bee3_[i] = new Circle(bx[i]+Gdx.graphics.getWidth()/26,by[3][i]+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/30);

            }

            //  bx -= Gdx.graphics.getWidth()/140;
            if(Gdx.input.justTouched()){
                gusy += Gdx.graphics.getHeight()/6;
            }
            else {
                gusy -= Gdx.graphics.getHeight()/140;
                if (gusy<=50.0){
                    gusy= (float) 250.0;
                    gamestate = 2;
                }
                else if(gusy>=(Gdx.graphics.getHeight()-20.0)){
                    gusy = (float) (Gdx.graphics.getHeight()-100.0);

                }

            }


        }
        if(Gdx.input.justTouched()){

            if(gamestate == 2){
              //  bx=Gdx.graphics.getWidth();
            }
            gamestate = 1;

        }


        //


	    batch.draw(gus,gusx,gusy,Gdx.graphics.getWidth()/13,Gdx.graphics.getHeight()/11);




	    batch.end();
	    gus_.set(gusx+Gdx.graphics.getWidth()/26,gusy+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/30);
	    //sr.begin(ShapeRenderer.ShapeType.Filled);
	    //sr.setColor(Color.BLACK);
	    //sr.circle(gus_.x,gus_.y,gus_.radius);

	    for(int i=0;i<nbees;i++){

       //     sr.circle(bx[i]+Gdx.graphics.getWidth()/26,by[1][i]+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/26);
        //    sr.circle(bx[i]+Gdx.graphics.getWidth()/26,by[2][i]+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/26);
       //     sr.circle(bx[i]+Gdx.graphics.getWidth()/26,by[3][i]+Gdx.graphics.getHeight()/26,Gdx.graphics.getWidth()/26);
            if(Intersector.overlaps(gus_,bee1_[i]) || Intersector.overlaps(gus_,bee2_[i]) || Intersector.overlaps(gus_,bee3_[i])){
                gamestate = 2;

            }
        }
        //sr.end();
	}
	
	@Override
	public void dispose () {
	}


}
