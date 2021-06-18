import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class PSO extends Recherche{

    int nbParticule,nbIteration;
    BigInteger vmax;
    float c1,c2,w,r1,r2;
    ArrayList<Particule> particules= new ArrayList<Particule>();
    Particule[] pbest;
    Particule gbest;

    public PSO(String[][] data, int variableLength, int nbC, int nbParticule,
               int nbIteration, BigInteger vmax, float c1, float c2, float w) {
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
            particules.add(new Particule(randomParticule(),new BigInteger("1") ));
            particules.get(i).score=testAstar(particules.get(i).position);
            pbest[i]=particules.get(i);

            if(pbest[i].score>gbest.score){
                gbest=pbest[i];
            }

        }
        initR();

    }

    public ReturnClass psoAlgorithm(){

        for (int i = 0; i < nbIteration; i++) {
            System.out.println("iteration : "+i);
            for (int j = 0; j < particules.size(); j++) {
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
            System.out.println("gbest score : "+gbest.score);

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
        for (int i = 0; i < particules.size(); i++) {
            if(particules.get(i).score>=gbest.score){
                gbest=particules.get(i);
            }
        }
    }

    public Particule updateVelocity(Particule particule, int i){
        Particule newPar;
        BigInteger currentPos=binaryToDecimal(particule.position);
        //calculate v(t+1)
        BigDecimal yo1=BigDecimal.valueOf(1/(c1*r1));
        BigDecimal yo2=BigDecimal.valueOf(1/(c2*r2));
        BigDecimal yo3=BigDecimal.valueOf(1/w);

        BigInteger v=(particule.velocity.divide(yo3.toBigInteger())).add ((binaryToDecimal(pbest[i].position)
                .subtract(currentPos)).divide(yo1.toBigInteger())).add((binaryToDecimal(gbest.position)
                .subtract(currentPos)).divide(yo2.toBigInteger()));
        initR();

        BigInteger truevmax=new BigInteger(String.valueOf(vmax));
        //verify if v is superior than vmax
        if(v.compareTo(truevmax) > 0){
            v=truevmax;
        }
        if(v.compareTo(truevmax.negate()) < 0){
            v=truevmax.negate();
        }

        //update particle position
        BigInteger x=currentPos.add(v);
        newPar= new Particule(decimalToBinary(x),v);
        return newPar;
    }



    public BigInteger binaryToDecimal(int[] vars){
        String s="";
        for (int i = 0; i < vars.length; i++) {
            s+=vars[variableLength-i-1];
        }
        BigInteger big= new BigInteger(s,2);
        return big;
    }

    public int[] decimalToBinary(BigInteger var){
        int[] vars= new int[variableLength];
        String binary= var.toString(2);
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
