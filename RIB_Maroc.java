package com_rib;

import java.io.Serializable;

import com_lieu.BanqueMarocaine;
import com_lieu.CO_BanqueMar;
import com_lieu.CO_Ville;
import com_lieu.ParameterHolder_BanqueMarocaine;
import com_lieu.ParameterHolder_Ville;
import com_lieu.Ville;
import commun.ParameterHolder_SageErreur;
import commun.SageErreur;
import commun.interfaces.SO_GestionErreurs_proxy;

import displayproject.binding.beans.Observable;
import ergoconst.Constants;
import framework.Array_Of_TextData;
import framework.ParameterHolder_TextData;
import framework.ParameterHolder_integer;
import framework.ParameterHolder_string;
import framework.RuntimeProperties;
import framework.TextData;

/**
 * RIB_Maroc<p>
 * <p>
 * @author Generated from Forte
 * @since  22-Mar-2009
 */
@RuntimeProperties(isDistributed=false, isAnchored=false, isShared=false, isTransactional=false)
 
public class RIB_Maroc
        extends RIB
        implements Serializable, Observable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4468142999565183875L;
	// ----------
    // Attributes
    // ----------
    private TextData clefRIB;
    private TextData codeBanque;
    private TextData codeVille;

    // ------------
    // Constructors
    // ------------
    public RIB_Maroc() {
        // Explicitly call the superclass constructor to prevent the implicit call
        super();
        this.setClefRIB(new TextData());
        this.setCodeBanque(new TextData());
        this.setCodeVille(new TextData());

    }

    // ----------------------
    // Accessors and Mutators
    // ----------------------
    public void setClefRIB(TextData clefRIB) {
        TextData oldValue = this.clefRIB;
        this.clefRIB = clefRIB;
        if (this.qq_Listeners != null) this.qq_Listeners.firePropertyChange("clefRIB", oldValue, this.clefRIB);
    }

    public TextData getClefRIB() {
        return this.clefRIB;
    }

    public void setCodeBanque(TextData codeBanque) {
        TextData oldValue = this.codeBanque;
        this.codeBanque = codeBanque;
        if (this.qq_Listeners != null) this.qq_Listeners.firePropertyChange("codeBanque", oldValue, this.codeBanque);
    }

    public TextData getCodeBanque() {
        return this.codeBanque;
    }

    public void setCodeVille(TextData codeVille) {
        TextData oldValue = this.codeVille;
        this.codeVille = codeVille;
        if (this.qq_Listeners != null) this.qq_Listeners.firePropertyChange("codeVille", oldValue, this.codeVille);
    }

    public TextData getCodeVille() {
        return this.codeVille;
    }

    // -------
    // Methods
    // -------
    /**
     * calculerClef<p>
     * bug 2007232 : ne pas modifier les lignes jusqu'au commentaire Fin bug 2007232<br>
     * <p>
     * @param clef Type: String
     * @param erreur Type: SageErreur
     * @return int
     */
    public int calculerClef(ParameterHolder_string clef, ParameterHolder_SageErreur erreur) {
        clef.setObject("");
        // Fin bug 2007232

        return Constants.CODERETOUROK;
    }

    /**
     * calculerReste<p>
     * bug 2007232 : ne pas modifier les lignes jusqu'au commentaire Fin bug 2007232<br>
     * <p>
     * @param chDiv Type: TextData
     * @param diviseur Type: int
     * @param reste Type: int
     * @return int
     */
    public int calculerReste(TextData chDiv, int diviseur, ParameterHolder_integer reste) {
        reste.setInt(0);
        // Fin bug 2007232

        int qq_long = chDiv.getActualSize();
        TextData part1 = null;
        TextData part2 = null;
        int resteInterm = 0;
        TextData chResteInterm = new TextData();

        if (qq_long > 9) {
            part1 = chDiv.copyRange(0, 9);
            part2 = chDiv.copyRange(9);
            // ------------------------------------
            // Parameters for call to CalculerReste
            // ------------------------------------
            ParameterHolder_integer qq_Reste = new ParameterHolder_integer();
            this.calculerReste(part1, diviseur, qq_Reste);
            resteInterm = qq_Reste.getInt();
            chResteInterm.setValue(resteInterm);
            chResteInterm.concat(part2);
            part2.setValue(chResteInterm.getValue());
            // ------------------------------------
            // Parameters for call to CalculerReste
            // ------------------------------------
            ParameterHolder_integer qq_Reste1 = new ParameterHolder_integer();
            this.calculerReste(part2, diviseur, qq_Reste1);
            reste.setInt(qq_Reste1.getInt());
        }
        else {
            int valeur = chDiv.getIntegerValue();
            reste.setInt(valeur % diviseur);
        }

        return Constants.CODERETOUROK;
    }

    /**
     * chargerEtVerifier<p>
     * - méthode enchainant chargement et vérification d'un RIB marocain<br>
     * - à appeler dans les classes filles<br>
     * <p>
     * @param pi_ValeurEntree Type: TextData
     * @param po_Erreur Type: SageErreur
     * @return int
     */
    public int chargerEtVerifier(TextData pi_ValeurEntree, ParameterHolder_SageErreur po_Erreur) {
        int retour = 0;

        // ------------------------------
        // Parameters for call to Charger
        // ------------------------------
        ParameterHolder_SageErreur qq_Erreur = new ParameterHolder_SageErreur();
        int qq_Charger = this.charger(pi_ValeurEntree, qq_Erreur);
        po_Erreur.setObject((SageErreur)qq_Erreur.getObject());
        retour = qq_Charger;
        if (retour != Constants.CODERETOUROK) {
            return retour;
        }

        // ---------------------------------------
        // Parameters for call to VerifierValidite
        // ---------------------------------------
        ParameterHolder_SageErreur qq_Erreur1 = new ParameterHolder_SageErreur();
        int qq_VerifierValidite = this.verifierValidite(qq_Erreur1);
        po_Erreur.setObject((SageErreur)qq_Erreur1.getObject());
        retour = qq_VerifierValidite;
        if (retour != Constants.CODERETOUROK) {
            return retour;
        }

        return Constants.CODERETOUROK;
    }

    /**
     * getAPlat<p>
     * <p>
     * @return TextData
     */
    public TextData getAPlat() {
        TextData txt = new TextData();
        return txt;
    }

    /**
     * getCompte<p>
     * <p>
     * @return TextData
     */
    public TextData getCompte() {
        return new TextData("");
    }

    /**
     * getZoneVide<p>
     * <p>
     * @return TextData
     */
    public TextData getZoneVide() {
        return new TextData("");
    }

    /**
     * remplacerAlpha<p>
     * <p>
     * @param chRIB Type: TextData
     * @return int
     */
    public int remplacerAlpha(ParameterHolder_TextData chRIB) {
        int ret = 0;
        boolean bret = false;

        ((TextData)chRIB.getObject()).setOffset(0);

        while (!(((TextData)chRIB.getObject()).isAtEnd())) {
            if (((TextData)chRIB.getObject()).isChar("ajAJ")) {
                ((TextData)chRIB.getObject()).replaceRange("1", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("bksBKS")) {
                ((TextData)chRIB.getObject()).replaceRange("2", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("cltCLT")) {
                ((TextData)chRIB.getObject()).replaceRange("3", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("dmuDMU")) {
                ((TextData)chRIB.getObject()).replaceRange("4", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("envENV")) {
                ((TextData)chRIB.getObject()).replaceRange("5", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("fowFOW")) {
                ((TextData)chRIB.getObject()).replaceRange("6", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("gpxGPX")) {
                ((TextData)chRIB.getObject()).replaceRange("7", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("hqyHQY")) {
                ((TextData)chRIB.getObject()).replaceRange("8", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            else if (((TextData)chRIB.getObject()).isChar("irzIRZ")) {
                ((TextData)chRIB.getObject()).replaceRange("9", ((TextData)chRIB.getObject()).getOffset(), ((TextData)chRIB.getObject()).getOffset()+1);
            }
            ((TextData)chRIB.getObject()).moveNext();
        }

        return Constants.CODERETOUROK;
    }

    /**
     * verifierBanqueDansVille<p>
     * <p>
     * @param po_Erreur Type: SageErreur
     * @return int
     */
    public int verifierBanqueDansVille(ParameterHolder_SageErreur po_Erreur) {
        int ret = Constants.CODERETOUROK;
        CO_BanqueMar unCO = new CO_BanqueMar();
        // ------------------------------------------------
        // Parameters for call to VerifierPresenceDansVille
        // ------------------------------------------------
        ParameterHolder_SageErreur qq_erreur = new ParameterHolder_SageErreur();
        int qq_VerifierPresenceDansVille = unCO.verifierPresenceDansVille(this.getCodeBanque().getValue(), this.getCodeVille().getValue(), qq_erreur);
        po_Erreur.setObject((SageErreur)qq_erreur.getObject());
        ret = qq_VerifierPresenceDansVille;
        return ret;
    }

    /**
     * verifierClef<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierClef(ParameterHolder_SageErreur erreur) {
        String clef = null;
        // -----------------------------------
        // Parameters for call to VerifierClef
        // -----------------------------------
        ParameterHolder_string qq_Clef = new ParameterHolder_string();
        ParameterHolder_SageErreur qq_Erreur = new ParameterHolder_SageErreur();
        int qq_VerifierClef = this.verifierClef(qq_Clef, qq_Erreur);
        clef = (String)qq_Clef.getObject();
        erreur.setObject((SageErreur)qq_Erreur.getObject());
        return qq_VerifierClef;
    }

    /**
     * verifierClef<p>
     * bug 2007232 : ne pas modifier les lignes jusqu'au commentaire Fin bug 2007232<br>
     * <p>
     * @param clef Type: String
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierClef(ParameterHolder_string clef, ParameterHolder_SageErreur erreur) {
        clef.setObject("");
        // Fin bug 2007232

        TextData laClef = new TextData();
        int ret = 0;
        SageErreur err = null;

        // -----------------------------------
        // Parameters for call to CalculerClef
        // -----------------------------------
        ParameterHolder_string qq_Clef = new ParameterHolder_string();
        ParameterHolder_SageErreur qq_Erreur = new ParameterHolder_SageErreur();
        int qq_CalculerClef = this.calculerClef(qq_Clef, qq_Erreur);
        laClef.setValue(((String)qq_Clef.getObject()));
        err = (SageErreur)qq_Erreur.getObject();
        ret = qq_CalculerClef;

        this.debug(framework.Constants.SP_MT_DEBUG, framework.Constants.SP_ST_USER10, 1, 1, "Clé du compte : ");
        this.debugLine(framework.Constants.SP_MT_DEBUG, framework.Constants.SP_ST_USER10, 1, 1, (String)clef.getObject());

        clef.setObject(laClef.getValue());

        if ((laClef.isNotEqual(this.getClefRIB())).getValue()) {
            Array_Of_TextData<TextData> tabParam = new Array_Of_TextData<TextData>();
            tabParam.set(0, new TextData());
            tabParam.get(0).setValue(this.getClefRIB().getValue());
            erreur.setObject(SO_GestionErreurs_proxy.getInstance().creerErreur(new TextData("AG030"), Constants.CR_INFO, tabParam));
            return Constants.CODERETOURKO;
        }
        return Constants.CODERETOUROK;
    }

    /**
     * verifierCodeBanque<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierCodeBanque(ParameterHolder_SageErreur erreur) {
        int ret = Constants.CODERETOUROK;
        CO_BanqueMar unCO = new CO_BanqueMar();
        BanqueMarocaine laBanque = null;
        // ---------------------------
        // Parameters for call to Lire
        // ---------------------------
        ParameterHolder_BanqueMarocaine qq_Banque = new ParameterHolder_BanqueMarocaine();
        ParameterHolder_SageErreur qq_Erreur = new ParameterHolder_SageErreur();
        int qq_Lire = unCO.lire(this.getCodeBanque().getValue(), qq_Banque, qq_Erreur);
        laBanque = (BanqueMarocaine)qq_Banque.getObject();
        erreur.setObject((SageErreur)qq_Erreur.getObject());
        ret = qq_Lire;
        return ret;
    }

    /**
     * verifierCodeVille<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierCodeVille(ParameterHolder_SageErreur erreur) {
        int ret = Constants.CODERETOUROK;
        CO_Ville unCO = new CO_Ville();
        Ville laVille = null;
        // ---------------------------
        // Parameters for call to Lire
        // ---------------------------
        ParameterHolder_Ville qq_LaVille = new ParameterHolder_Ville();
        ParameterHolder_SageErreur qq_Erreur = new ParameterHolder_SageErreur();
        int qq_Lire = unCO.lire(this.getCodeVille().getValue(), qq_LaVille, qq_Erreur);
        laVille = (Ville)qq_LaVille.getObject();
        erreur.setObject((SageErreur)qq_Erreur.getObject());
        ret = qq_Lire;
        return ret;
    }

    /**
     * verifierValidite<p>
     * Vérification de la validité du RIB marocain:<br>
     * . code banque correct<br>
     * . code ville correct<br>
     * . cohérence entre code banque et code ville<br>
     * . clée correcte<br>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierValidite(ParameterHolder_SageErreur erreur) {
        int ret = 0;

        // -----------------------------------------
        // Parameters for call to VerifierCodeBanque
        // -----------------------------------------
        ParameterHolder_SageErreur qq_Erreur = new ParameterHolder_SageErreur();
        int qq_VerifierCodeBanque = this.verifierCodeBanque(qq_Erreur);
        erreur.setObject((SageErreur)qq_Erreur.getObject());
        ret = qq_VerifierCodeBanque;
        if (ret != Constants.CODERETOUROK) {
            return ret;
        }

        // ----------------------------------------
        // Parameters for call to VerifierCodeVille
        // ----------------------------------------
        ParameterHolder_SageErreur qq_Erreur1 = new ParameterHolder_SageErreur();
        int qq_VerifierCodeVille = this.verifierCodeVille(qq_Erreur1);
        erreur.setObject((SageErreur)qq_Erreur1.getObject());
        ret = qq_VerifierCodeVille;
        if (ret != Constants.CODERETOUROK) {
            return ret;
        }
        //Migration20090120 mais ça sert à rien ça !!!
        //YOB20090120 il faut inhiber ce controle
        // 
        // ret = VerifierBanqueDansVille(Erreur);
        // If (ret != CODERETOUROK) Then
        //         return(ret);
        // End if;
        // -----------------------------------
        // Parameters for call to VerifierClef
        // -----------------------------------
        ParameterHolder_SageErreur qq_Erreur2 = new ParameterHolder_SageErreur();
        int qq_VerifierClef = this.verifierClef(qq_Erreur2);
        erreur.setObject((SageErreur)qq_Erreur2.getObject());
        ret = qq_VerifierClef;
        if (ret != Constants.CODERETOUROK) {
            return ret;
        }

        return Constants.CODERETOUROK;
    }
}	// end class RIB_Maroc
// c Pass 2 Conversion Time: 6234 milliseconds
