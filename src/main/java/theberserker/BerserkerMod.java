package theberserker;


import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import theberserker.cards.*;
import theberserker.patches.AbstractCardEnum;

@SpireInitializer
public class BerserkerMod implements EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber {

  // get the paths to the images of the card backgrounds
  private static final String ATTACK_ORANGE = makePath("512/bg_attack_orange.png");
  private static final String SKILL_ORANGE = makePath("512/bg_skill_orange.png");
  private static final String POWER_ORANGE = makePath("512/bg_power_orange.png");
  private static final String ORB_ORANGE = makePath("512/card_orange_orb.png");
  
  private static final String ATTACK_ORANGE_PORTRAIT =
      makePath("resources/1024/bg_attack_orange.png");
  private static final String SKILL_ORANGE_PORTRAIT = makePath("1024/bg_skill_orange.png");
  private static final String POWER_ORANGE_PORTRAIT = makePath("1024/bg_power_orange.png");
  private static final String ORB_ORANGE_PORTRAIT = makePath("1024/card_orange_orb.png");
  private static final Color ORANGE = CardHelper.getColor(255, 150, 0);
  private static final String BERSERKER_MOD_ASSETS_FOLDER = "img";
  
  // berserker assets
  public static final String BERSERKER_PORTRAIT = "charSelect/berserkerPortrait.png";
  
  // get the paths to the images of the card art
  public static final String PLACEHOLDER_ART = makePath("cards/placeholder.png");

  public BerserkerMod() {
    // TODO: characters, character animations, cards, relics, keywords
    // note: use savable if you want to save a value in a relic/card ie: like the
    // pen nib
    // Adding orange as a color
    BaseMod.addColor(AbstractCardEnum.ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE,
        ORANGE, ATTACK_ORANGE, SKILL_ORANGE, POWER_ORANGE, ORB_ORANGE, ATTACK_ORANGE_PORTRAIT,
        SKILL_ORANGE_PORTRAIT, POWER_ORANGE_PORTRAIT, ORB_ORANGE_PORTRAIT);
  }

  public static void initialize() {
    new BerserkerMod();
  }

  @Override
  public void receiveEditStrings() {
    // TODO load the custom strings to the file.
    BaseMod.loadCustomStringsFile(CardStrings.class, "");
  }

  public static final String makePath(String path) {
    return BERSERKER_MOD_ASSETS_FOLDER + "/" + path;
  }

  @Override
  public void receiveEditCards() {
    BaseMod.addCard(new TestCard());
  }

  @Override
  public void receiveEditCharacters() {
    //TODO: create portrait and select button create the logger method
    //logger.info("begin edit characters");
    //BaseMod.addCharacter(new TheBerserker(TheBerserker.NAME), selectButtonPath, makePath(BERSERKER_PORTRAIT), TheBerserkerEnum.BERSERKER);
  }
  
}
