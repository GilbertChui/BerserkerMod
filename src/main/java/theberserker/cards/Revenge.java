package theberserker.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;


public class Revenge extends CustomCard{
  
  public static final String ID = "theBerserker:Revenge";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardString.UPGRADE_DESCRIPTION;
  private static final int COST = 2;
  
  public Revenge() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.ORANGE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
    
    this.baseDamage = 0;
  }
  
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeBaseCost(1);
    }
  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    
    this.baseDamage = GameActionManager.damageReceivedThisCombat;
    calculateCardDamage(m);
    
    if(this.damage < 40) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
      this.rawDescription = DESCRIPTION;
      initializeDescription();
    }else {
      if (m != null) {
          AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
       }
      AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
      this.rawDescription = DESCRIPTION;
      initializeDescription();
    }
  }
  
  @Override
  public AbstractCard makeCopy() {
    return new Revenge();
  }
  
  @Override
  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }
  
  @Override
  public void calculateCardDamage(AbstractMonster m) {
    super.calculateCardDamage(m);
    
    this.rawDescription = DESCRIPTION;
    this.rawDescription += UPGRADE_DESCRIPTION;
    initializeDescription();
  }
  
  @Override
  public void applyPowers() {
    this.baseDamage = GameActionManager.damageReceivedThisCombat;
    super.applyPowers();
    
    this.rawDescription = DESCRIPTION;
    this.rawDescription += UPGRADE_DESCRIPTION;
    initializeDescription();
  }
}
