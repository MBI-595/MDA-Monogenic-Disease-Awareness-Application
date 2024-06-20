package com.example.monogenicdiseases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import org.biojava.bio.seq.DNATools;
//import org.biojava.bio.structure.Structure;
//import org.biojava.bio.structure.align.util.AtomCache;
//import org.biojava.bio.symbol.SymbolList;
//import org.biojava.nbio.aaproperties.IPeptideProperties;
//import org.biojava.nbio.aaproperties.PeptidePropertiesImpl;

//import org.biojava.nbio.aaproperties.IPeptideProperties;
//import org.biojava.nbio.aaproperties.PeptidePropertiesImpl;
//import org.biojava.nbio.core.sequence.ProteinSequence;

import java.net.URL;

public class BioJava extends AppCompatActivity {

    Button align;
    TextView Seq1;
    TextView Seq2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_java);

        align = findViewById(R.id.bioJavaAlign);
        Seq1 = findViewById(R.id.bioSeq1);
        Seq2 = findViewById(R.id.bioSeq2);

        align.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//                    //make a dna symbolList
//                    SymbolList dna = DNATools.createDNA("actactgcgtcagtc");
//                    //convert it into Rna
//                    SymbolList rna = DNATools.toRNA(dna);
//                    //just to prove it works
//                    Seq1.setText(rna.seqString());
//                    //Biological Transcription
//                    rna = DNATools.transcribeToRNA(dna);
//                    Seq2.setText(rna.seqString());
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//
//                }

            }
        });
    }

        //GET STRUCTURE

//        AtomCache cache = new AtomCache();
//        try{
//            Structure s = cache.getStructure("2spl");
//            System.out.println(s);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        MOLECULAR ANALYSIS

//        ProteinSequence pSequence = new ProteinSequence("VLSPADKTNVKAAWGKVGAHAG");
//        IPeptideProperties pp = new PeptidePropertiesImpl();
//        System.out.println(pSequence.toString());
//        System.out.println("IsoelectricPoint :" + pp.getIsoelectricPoint(pSequence));
//        System.out.println("AA Composition :" + pp.getAAComposition(pSequence));
//        System.out.println("Aliphatic Index :" + pp.getApliphaticIndex(pSequence));
//        System.out.println("Molecular Weight :" + pp.getMolecularWeight(pSequence));
//        System.out.println("Net Charge :" + pp.getNetCharge(pSequence));
//        System.out.println("Extinction :" + pp.getExtinctionCoefficient(pSequence, true));

        //MULTIPLE SEQUENCE ALIGNMENT

//        String[] ids = new String[] {"P68871","P69891","P69892"};
//        try{
//            multipleSequenceAlignment(ids);
//        } catch(Exception e){
//            e.getMessage();
//        }
//    }
//    public static void multipleSequenceAlignment(String[] ids) throws Exception{
//        List<ProteinSequence> lst = new ArrayList<ProteinSequence>();
//        for (String id : ids){
//            lst.add(getSequenceForId(id));
//        }
//        Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(lst);
//        System.out.printf("Clustalw:%n%s%n",profile);
//        ConcurrencyTools.shutdown();
//    }
//    private static ProteinSequence getSequenceForId(String uniProtId) throws Exception {
//        URL uniprotFasta = new URL(String.format("https://www.uniprot.org/uniprot/%s.fasta",uniProtId ));
//        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);
//        System.out.printf("id : %s %s%n%s%n",uniProtId,seq,seq.getOriginalHeader());
//        return seq;
//    }


        public void onBackPressed () {
            super.onBackPressed();

            Intent i = new Intent(BioJava.this, Diseases.class);
            startActivity(i);
        }
    }
