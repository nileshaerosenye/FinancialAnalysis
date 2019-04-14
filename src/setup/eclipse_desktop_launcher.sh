#! /bin/sh

# This needs to be run as root with sudo

### -----------------------------------
echo "Eclipse | Eclipse desktop launcher into system applications directory"
echo "[Desktop Entry]" >> /usr/share/applications/eclipse.desktop
echo "Name=Eclipse IDE" >> /usr/share/applications/eclipse.desktop
echo "Comment=Eclipse IDE" >> /usr/share/applications/eclipse.desktop
echo "Type=Application" >> /usr/share/applications/eclipse.desktop
echo "Encoding=UTF-8" >> /usr/share/applications/eclipse.desktop
echo "Exec=/home/nileshmune/opt/eclipse/eclipse" >> /usr/share/applications/eclipse.desktop
echo "Icon=/home/nileshmune/opt/eclipse/icon.xpm" >> /usr/share/applications/eclipse.desktop
echo "Categories=GNOME;Application;Development;" >> /usr/share/applications/eclipse.desktop
echo "Terminal=false" >> /usr/share/applications/eclipse.desktop
echo "StartupNotify=true" >> /usr/share/applications/eclipse.desktop
