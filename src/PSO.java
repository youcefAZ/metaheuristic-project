import java.util.ArrayList;
import java.util.Random;

public class PSO extends Recherche{

    int nbParticule,nbIteration,vmax;
    float c1,c2,w,r1,r2;
    ArrayList<Particule> particules= new ArrayList<Particule>();
    Particule[] pbest;
    Particule gbest;

    public PSO(String[][] data, int variableLength, int nbC, int nbParticule,
               int nbIteration, int vmax, float c1, float c2, float w) {
        super(data, variableLength, nbC);
        this.nbParticule = nbParticule;
        this.nbIteration = nbIteration;
        this.vmax = vmax;
        this.c1 = c1;
        this.c2 = c2;
        this.w = w;
        pbest=new Particule[nbParticule];
        gbest= new Particule();
        for (int i = 0; i < nbParticule; i++) {
            particules.add(new Particule(randomParticule(),(float)0.2));
            particules.get(i).score=testAstar(particules.get(i).position);
            printArray(particules.get(i).position);
            pbest[i]=particules.get(i);

            if(pbest[i].score>gbest.score){
                gbest=pbest[i];
            }

        }
        printArray(gbest.position);
        System.out.println("score : "+gbest.score);
        initR();

    }

    public ReturnClass psoAlgorithm(){

        for (int i = 0; i < nbIteration; i++) {
            System.out.println("iteration : "+i);
            for (int j = 0; j < particules.size(); j++) {
                System.out.println("particule "+j);
                //update position and velocity or the current particle
                Particule newp=updateVelocity(particules.get(j),j);
                newp.score=testAstar(newp.position);
                particules.set(j,newp);

                //update pbest
                if(newp.score>=pbest[j].score){
                    pbest[j]=newp;
                }

            }
            //update gbest
            updateGbest();
            if(gbest.score==nbC){
                System.out.println("found");
                ReturnClass ret= new ReturnClass(gbest.position,gbest.score,true);
                return ret;
            }
        }

        System.out.println("not found, best score : "+gbest.score);
        ReturnClass ret= new ReturnClass(gbest.position,gbest.score,false);
        return ret;
    }


    public void updateGbest(){
        System.out.println("updating gbest");
        for (int i = 0; i < particules.size(); i++) {
            if(particules.get(i).score>=gbest.score){
                gbest=particules.get(i);
            }
        }
    }

    public Particule updateVelocity(Particule particule, int i){
        System.out.println("updating velocity");
        Particule newPar;
        int currentPos=binaryToDecimal(particule.position);
        //calculate v(t+1)
        int v=Math.round(w*particule.velocity+ c1*r1*(binaryToDecimal(pbest[i].position)-currentPos)
                + c2*r2*(binaryToDecimal(gbest.position)-currentPos)) ;
        initR();

        //verify if v is superior than vmax
        if(v>vmax || v<-vmax){
            if(v<0){
                v=-vmax;
            }
            else {
                v=vmax;
            }
        }

        //update particle position
        int x=currentPos+v;
        newPar= new Particule(decimalToBinary(x),v);
        printArray(newPar.position);
        return newPar;
    }



    public int binaryToDecimal(int[] vars){
        System.out.println("binary to decimal");
        String s="";
        for (int i = 0; i < vars.length; i++) {
            s+=vars[variableLength-i-1];
        }
        return Integer.parseInt(s,2);
    }

    public int[] decimalToBinary(int var){
        System.out.println("decimal to binary");
        int[] vars= new int[variableLength];
        String binary= Integer.toBinaryString(var);
        System.out.println(binary);
        for (int i = 0; i < variableLength; i++) {
            if(i<binary.length()){
                vars[variableLength-i-1]=Integer.parseInt(String.valueOf(binary.charAt(i)));
            }
            else {
                vars[variableLength-i-1]=0;
            }
        }
        return vars;
    }


    public void initR(){
        Random rand= new Random();
        r1=rand.nextFloat();
        r2=rand.nextFloat();
    }


}
