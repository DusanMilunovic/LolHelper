using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HeroPickerFront
{
    class ChampionPicksAndPosition
    {
        public List<ChampionPick> picks { get; set; }
        public String position { get; set; }

        public ChampionPicksAndPosition(List<ChampionPick> pickss, String positionn)
        {
            picks = pickss;
            position = positionn;
        }

        public string toJson()
        {
            string retval = "{\n\t\"picks\": [\n";
            for (int i = 0; i < picks.Count; i++)
            {
                var pick = picks[i];
                retval += "\t\t{\n\t\t\t\"champion\": \"" + pick.champion + "\",\n";
                retval += "\t\t\t\"myTeam\": " + pick.myTeam.ToString().ToLower() + ",\n";
                retval += "\t\t\t\"myChamp\": " + pick.myChamp.ToString().ToLower() + "\n\t\t}";
                if (i < picks.Count - 1)
                {
                    retval += ",\n";
                }
            }
            retval += "\n\t\t],\n\t\"position\": \"" + position + "\"\n}";
            return retval;
        }
    }
}
