# ==============================================
# AUTO-LANCEMENT NAVSAFE SURVEILLANCE
# ==============================================
if [ -f "$HOME/Navsafe/surveillance_totale.sh" ] && ! pgrep -f "surveillance_totale.sh" >/dev/null; then
    echo "🚀 Démarrage Navsafe Surveillance..."
    cd ~/Navsafe
    ./surveillance_totale.sh demarrer &
fi
