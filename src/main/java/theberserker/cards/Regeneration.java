package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class Regeneration extends CustomCard {
  
  public static final String ID = "theBerserker:Regeneration";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final String UPGRADED_DESCRIPTION = cardString.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int REGEN_AMT = 1;
  private static final int HEAL = 3;

  public Regeneration() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

    this.magicNumber = this.baseMagicNumber = HEAL;
    this.exhaust = true;
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.exhaust = false;
      this.rawDescription = UPGRADED_DESCRIPTION;
      initializeDescription();
    }
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager
        .addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, REGEN_AMT), REGEN_AMT));
    AbstractDungeon.player.heal(this.magicNumber);

  }

  @Override
  public AbstractCard makeCopy() {
    return new Regeneration();
  }
}
