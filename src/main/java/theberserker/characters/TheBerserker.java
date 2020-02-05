package theberserker.characters;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import basemod.animations.SpriterAnimation;
import theberserker.cards.StrikeB;
import theberserker.patches.AbstractCardEnum;
import theberserker.patches.TheBerserkerEnum;
import theberserker.relics.Rock;

public class TheBerserker extends CustomPlayer {

  public static final String NAME = "The Berserker";
  public static final int ENERGY_PER_TURN = 3;
  public static final int STARTING_HP = 110;
  public static final int MAX_HP = 100;
  public static final int STARTING_GOLD = 99;
  public static final int HAND_SIZE = 5;
  public static final String THE_BERSERKER_SHOULDER_2 =
      "img/char/theBerserker/theBerserkerShoulder2.png";
  public static final String THE_BERSERKER_SHOULDER_1 =
      "img/char/theBerserker/theBerserkerShoulder.png";
  public static final String THE_BERSERKER_CORPSE = "img/char/theBerserker/theBerserkerCorpse.png";
  // public static final String THE_BERSERKER_SKELETON_ATLAS = "";
  // public static final String THE_BERSERKER_SKELETON_JSON = "";
  public static final String[] ENERGY_ORB = {"img/char/theBerserker/orb/orb1.png",
      "img/char/theBerserker/orb/orb2.png", "img/char/theBerserker/orb/orb3.png",
      "img/char/theBerserker/orb/orb4.png", "img/char/theBerserker/orb/orb5.png",
      "img/char/theBerserker/orb/orb6.png", "img/char/theBerserker/orb/orb1d.png",
      "img/char/theBerserker/orb/orb2d.png", "img/char/theBerserker/orb/orb3d.png",
      "img/char/theBerserker/orb/orb4d.png", "img/char/theBerserker/orb/orb5d.png"};
  public static final String ORB_VFX_PATH = "img/char/theBerserker/orb/vfx.png";
  public static final float[] LAYER_SPEEDS = {1, 2, 1, 3, 2, 1, 1, 1, 1, 1, 1};
  public static final SpriterAnimation ANIMATION =
      new SpriterAnimation("img/char/theBerserker/berserker.scml"); // should point
  // to a spriter
  // animation

  public TheBerserker(String name) {
    super(NAME, TheBerserkerEnum.THE_BERSERKER, ENERGY_ORB, ORB_VFX_PATH, LAYER_SPEEDS, ANIMATION);
    this.dialogX = (this.drawX + 0.0F * Settings.scale); // set the dialog boxes for the character
    this.dialogY = (this.drawY + 220.0F * Settings.scale);

    initializeClass(null, THE_BERSERKER_SHOULDER_2, THE_BERSERKER_SHOULDER_1, THE_BERSERKER_CORPSE,
        getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

  }

  public ArrayList<String> getStartingDeck() {
    ArrayList<String> deck = new ArrayList<>();
    deck.add("theBerserker:StrikeB");
    deck.add("theBerserker:StrikeB");
    deck.add("theBerserker:StrikeB");
    deck.add("theBerserker:StrikeB");
    deck.add("theBerserker:StrikeB");
    deck.add("theBerserker:Regeneration");
    deck.add("theBerserker:Regeneration");
    deck.add("theBerserker:Regeneration");
    deck.add("theBerserker:Regeneration");
    deck.add("theBerserker:RecklessCleave");
    deck.add("theBerserker:WhirlwindOfDeath");
    return deck;
  }

  public ArrayList<String> getStartingRelics() {

    ArrayList<String> relics = new ArrayList<>();
    relics.add(Rock.ID);

    UnlockTracker.markRelicAsSeen(Rock.ID);
    return relics;
  }

  public CharSelectInfo getLoadout() {
    return new CharSelectInfo("The Berserker", "AAAAAAAAAAAAAAAAAAAAAA", STARTING_HP, MAX_HP, 0,
        STARTING_GOLD, HAND_SIZE, this, getStartingRelics(), getStartingDeck(), false);

  }

  @Override
  public void doCharSelectScreenSelectEffect() {
    CardCrawlGame.sound.playA("ATTACK_HEAVY", -.5f);
    CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED,
        true);
  }

  @Override
  public int getAscensionMaxHPLoss() {
    return 10;
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
        AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SMASH,
        AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
  }

  @Override
  public String getSpireHeartText() {
    return "NL ME SMASH!";
  }

  @Override
  public AbstractCard getStartCardForEvent() {
    return new StrikeB();
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
        + "NL ~\"Join~ ~us,~ ~oh one of never ending rage,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~"
        + " ~Spire.\"~";
  }

  @Override
  public AbstractPlayer newInstance() {
    return new TheBerserker(NAME);
  }
}
