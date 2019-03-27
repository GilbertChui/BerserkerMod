package theberserker.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class AxeAQuestion extends CustomCard {
  public static final String ID = "AxeAQuestion";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  private static final String NAME = cardString.NAME;
  private static final int COST = 1;
  private static final int DEBUFF_AMT = 2;
  private static final int UPGRADE_DEBUFF = 1;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  
  public AxeAQuestion(){
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
    this.baseMagicNumber = this.magicNumber = DEBUFF_AMT;
  }
  
  @Override
  public void use( AbstractPlayer p, AbstractMonster m) {
    //TODO: figure out what this card does.
    AbstractDungeon.actionManager.addToBottom();
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeMagicNumber(UPGRADE_DEBUFF);
    }
    
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new AxeAQuestion();
  }
}
