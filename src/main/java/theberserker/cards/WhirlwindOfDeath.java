package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;
import theberserker.powers.WhirlwindOfDeathPower;

public class WhirlwindOfDeath extends CustomCard{
  public static final String ID = "WhirlwindOfDeath";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final String UPGRADED_DESCRIPTION = cardString.UPGRADE_DESCRIPTION;
  public static final int DAMAGE = 6;
  private static final int COST = 3;

  public WhirlwindOfDeath() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.POWER,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = this.magicNumber = DAMAGE;

  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.rawDescription = UPGRADED_DESCRIPTION;
      initializeDescription();
      
    }
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    //TODO: make thsi work with strength buffs. also test this garbage.
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WhirlwindOfDeathPower(p, this.magicNumber), this.magicNumber));
  }

  @Override
  public AbstractCard makeCopy() {
    return new WhirlwindOfDeath();
  }
}