package theberserker.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;


public class Revenge extends CustomCard{
  public static final String ID = "theBerserker:Revenge";
  public static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final String UPGRADED_DESCRIPTION = cardString.UPGRADE_DESCRIPTION;
  public static final int COST = 2;
  
  public Revenge() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
    this.damage = this.baseDamage = GameActionManager.damageReceivedThisCombat;
  }
  
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.updateCost(1);
      this.rawDescription = UPGRADED_DESCRIPTION;
      initializeDescription();
    }
  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    if (!this.upgraded) {
      this.damage = GameActionManager.damageReceivedThisCombat;
    }else {
      this.damage = GameActionManager.damageReceivedThisCombat * 2;
    }
    if(this.damage < 20) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }else {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new Revenge();
  }
}
