package com.example.monogenicdiseases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import org.biojava.nbio.aaproperties.IPeptideProperties;
//import org.biojava.nbio.aaproperties.PeptidePropertiesImpl;
//import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
//import org.biojava.nbio.core.sequence.ProteinSequence;


public class Struct extends AppCompatActivity {
    TextView ST;
    public String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struct);
        ST=findViewById(R.id.atom);

//        ProteinSequence pSequence = null;
//        try {
//            pSequence = new ProteinSequence("VLSPADKTNVKAAWGKVGAHAG");
//            IPeptideProperties pp = new PeptidePropertiesImpl();
////            System.out.println(pSequence.toString());
//            ST.setText("IsoelectricPoint: "+pp.getIsoelectricPoint(pSequence));
////            System.out.println("IsoelectricPoint :" + pp.getIsoelectricPoint(pSequence));
////            System.out.println("AA Composition :" + pp.getAAComposition(pSequence));
////            System.out.println("Aliphatic Index :" + pp.getApliphaticIndex(pSequence));
////            System.out.println("Molecular Weight :" + pp.getMolecularWeight(pSequence));
////            System.out.println("Net Charge :" + pp.getNetCharge(pSequence));
////            System.out.println("Extinction :" + pp.getExtinctionCoefficient(pSequence, true));
//        } catch (CompoundNotFoundException e) {
//            e.printStackTrace();
//        }




//        Thread gfgThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    //AtomCache cache = new AtomCache();
//                   // FileParsingParameters params = new FileParsingParameters();
//                    //cache.setFileParsingParams(params);
//                    // cache.setUseMmCif(false);
//                    //cache.setUseMmtf(true);
//                    //StructureIO.setAtomCache(cache);
//                    //  ChemCompGroupFactory.setChemCompProvider(new DownloadChemCompProvider());
//                    //Structure s = StructureIO.getStructure("2X3T");
//                    CifFile cifFile;
//                     // Structure s = StructureIO.getStructure("4hhb");
//                    //ST.setText(s.toString());
//                    //System.out.println(s);
//                }catch (Exception e){
//                    Log.e("Error:",e.toString());
//                }
//            }
//        });

        //gfgThread.start();
//        ST=findViewById(R.id.atom);
//        String filename =  "C:\\Users\\user\\Downloads\\6bb5.pdb" ;
//        PDBFileReader pdbreader = new PDBFileReader();
//        try{
//            Structure struc = pdbreader.getStructure(filename);
//            data=struc.toString();
//
//        } catch (Exception e){
//    e.printStackTrace();
//}
//        ST.setText(data);

//        ST=findViewById(R.id.atom);
//        AtomCache cache = new AtomCache();
//        try{
//            Structure s = cache.getStructure("2spl");
//
//            ST.setText(s.toString());
//            System.out.println(s);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(Struct.this, Home.class);
        startActivity(i);
    }
}