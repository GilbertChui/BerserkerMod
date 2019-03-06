package theberserker.characters;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import basemod.abstracts.CustomPlayer;
import theberserker.patches.AbstractCardEnum;
import theberserker.patches.TheBerserkerEnum;

public class TheBerserker extends CustomPlayer {

  public static final String NAME = "The Berserker";
  public static final int ENERGY_PER_TURN = 3;
  public static final int STARTING_HP = 120;
  public static final int MAX_HP = 120;
  public static final int STARTING_GOLD = 99;
  public static final int HAND_SIZE = 5;
  // TODO: make the following variables point to their respective paths
  public static final String THE_BERSERKER_SHOULDER_2 = "";
  public static final String THE_BERSERKER_SHOULDER_1 = "";
  public static final String THE_BERSERKER_CORPSE = "";
  public static final String THE_BERSERKER_SKELETON_ATLAS = "";
  public static final String THE_BERSERKER_SKELETON_JSON = "";
  public static final String[] energyOrb = {"", "", "", "", "", "", "", "", "", "", ""};

  public TheBerserker(String name) {
    // TODO: replace name with the respective paths: path to orb vfx.png
    // path to berserker.g3dj, Berserker_Render|idle
    super(name, TheBerserkerEnum.THE_BERSERKER, energyOrb, name, name, name);
    this.dialogX = (this.drawX + 0.0F * Settings.scale);
    this.dialogY = (this.drawY + 220.0F * Settings.scale);

    initializeClass(null, THE_BERSERKER_SHOULDER_2, THE_BERSERKER_SHOULDER_1, THE_BERSERKER_CORPSE,
        getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

    loadAnimation(THE_BERSERKER_SKELETON_ATLAS, THE_BERSERKER_SKELETON_JSON, 1.0F);

    AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
    e.setTime(e.getEndTime() * MathUtils.random());
  }

  public ArrayList<String> getStartingDeck() {
    ArrayList<String> deck = new ArrayList<>();
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    deck.add("TestCard");
    return deck;
  }

  public ArrayList<String> getStartingRelics() {
    ArrayList<String> relics = new ArrayList<>();
    relics.add("ROCK");
    UnlockTracker.markRelicAsSeen("ROCK");
    return relics;
  }

  public CharSelectInfo getLoadout() {
    return new CharSelectInfo("The Berserker", "ARG", STARTING_HP, MAX_HP, 0, STARTING_GOLD,
        HAND_SIZE, this, getStartingRelics(), getStartingDeck(), false);
  }

  @Override
  public void doCharSelectScreenSelectEffect() {
    CardCrawlGame.sound.playA("ATTACK_HEAVY", 0);
    CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED,
        true);
  }

  @Override
  public int getAscensionMaxHPLoss() {
    return 4;
  }

  @Override
  public CardColor getCardColor() {
    return AbstractCardEnum.ORANGE;
  }

  @Override
  public Color getCardRenderColor() {
    return Color.ORANGE;
  }

  @Override
  public Color getCardTrailColor() {
    return Color.FIREBRICK;
  }

  @Override
  public String getCustomModeCharacterButtonSoundKey() {
    return "ATTACK_HEAVY";
  }

  @Override
  public BitmapFont getEnergyNumFont() {
    return FontHelper.energyNumFontRed;
  }

  @Override
  public String getLocalizedCharacterName() {
    return NAME;
  }

  @Override
  public Color getSlashAttackColor() {
    return Color.RED;
  }

  @Override
  public AttackEffect[] getSpireHeartSlashEffect() {
    return new AbstractGameAction.AttackEffect[] {AbstractGameAction.AttackEffect.BLUNT_HEAVY,
        AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY,
        AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.SMASH,
        AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.SMASH,
        AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.SMASH};
  }

  @Override
  public String getSpireHeartText() {
    return "NL ME SMASH!";
  }

  @Override
  public AbstractCard getStartCardForEvent() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getTitle(PlayerClass arg0) {
    return NAME;
  }

  @Override
  public String getVampireText() {
    return "Navigating an unlit street, you come across several hooded figures in the midst"
        + " of some dark ritual. As you approach, they turn to you in eerie unison. The "
        + "tallest among them bares fanged teeth and extends a long, pale hand towards you. "
        + "NL ~\"Join~ ~us,~ ~oh Mighty Warrior,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~"
        + " ~Spire.\"~";
  }

  @Override
  public AbstractPlayer newInstance() {
    return new TheBerserker(NAME);
  }
}
