package games.phanatic.kitpvp.cmds;

import code.matthew.psc.api.command.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dev extends ICommand {

    private File f;
    private FileWriter writer;
    private BufferedWriter bWriter;

    public Dev() {
        super("dev", "dev", "Dev", true);
        f = new File("dev.txt");
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for(int i = 0; i < p.getInventory().getContents().length; i++) {
            ItemStack stack = p.getInventory().getItem(i);
            addLine(i + ":" + stack.getType() + ":" + stack.getItemMeta().getDisplayName());
        }
        return true;
    }

    private void addLine(String line) {
        try {
            if (writer == null) {
                writer = new FileWriter(f);
                bWriter = new BufferedWriter(writer);
            }
            bWriter.write(line);
            bWriter.newLine();

            writer.close();

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
