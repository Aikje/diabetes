<?php
/**
 * The base configurations of the WordPress.
 *
 * This file has the following configurations: MySQL settings, Table Prefix,
 * Secret Keys, WordPress Language, and ABSPATH. You can find more information
 * by visiting {@link http://codex.wordpress.org/Editing_wp-config.php Editing
 * wp-config.php} Codex page. You can get the MySQL settings from your web host.
 *
 * This file is used by the wp-config.php creation script during the
 * installation. You don't have to use the web site, you can just copy this file
 * to "wp-config.php" and fill in the values.
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'RecoMA');

/** MySQL database username */
define('DB_USER', 'RecoMA');

/** MySQL database password */
define('DB_PASSWORD', '$_KoM0157');

/** MySQL hostname */
define('DB_HOST', 'localhost');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8');

/** The Database Collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         'n)zy}urM{jMgVEN=VwL2Q`[xtAe]Gkb_N:+IET.!KW#(k-UL$VSbn`g|!+SBj .P');
define('SECURE_AUTH_KEY',  '0G+kUY+#wPXHWO-<GRqW=ftbIJ78g=*zZbAA!yw&KnX2At *oxd#~!00])nk1~9u');
define('LOGGED_IN_KEY',    '.@<On%_Wi; &7$~OD|epmsFR)hM~cC)F1a,6(f}-!nEoN]7LZtM:P)FqqmY-~gU ');
define('NONCE_KEY',        '@_|OsYAAl4&M+F)oY{tw9+[IQM=eIAeP_POA}M)8|o&G&dQvr2O,%Y&K?W{yps8E');
define('AUTH_SALT',        '<a8Unlc D~?`SC0lpZsEIgo86sQj&C=ga)p89T}Fn-iK<,rG_fu-[_{cM,gCc`D,');
define('SECURE_AUTH_SALT', '?4Gd!}{Ki/-AUQb -!H=&J3&oeI9iV0B*@8!A3.-X Sk.)h|HF@y[7u-=*~|[w/b');
define('LOGGED_IN_SALT',   'ol@OTq(U]t^-~<JXctZqx$sh3F|=u:z82-PoufMm%0v|1-E+-,}H[BzjT&R.kK%<');
define('NONCE_SALT',       'GNc4WPdkY6i@4h61xF jQYX@Eerd<`GU$Y+h>7<rm#F_pys?Yh,r3|A+`5-Rp>[P');

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each a unique
 * prefix. Only numbers, letters, and underscores please!
 */
$table_prefix  = 'wp_ReCoMa_';

/**
 * WordPress Localized Language, defaults to English.
 *
 * Change this to localize WordPress. A corresponding MO file for the chosen
 * language must be installed to wp-content/languages. For example, install
 * de_DE.mo to wp-content/languages and set WPLANG to 'de_DE' to enable German
 * language support.
 */
define('WPLANG', 'nl_NL');

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 */
define('WP_DEBUG', false);

/* That's all, stop editing! Happy blogging. */

/** Absolute path to the WordPress directory. */
if ( !defined('ABSPATH') )
	define('ABSPATH', dirname(__FILE__) . '/');

/** Sets up WordPress vars and included files. */
require_once(ABSPATH . 'wp-settings.php');
