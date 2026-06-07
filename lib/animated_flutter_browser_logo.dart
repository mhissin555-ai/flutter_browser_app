import 'package:flutter/material.dart';

class AnimatedFlutterBrowserLogo extends StatelessWidget {
  final Duration animationDuration;
  final double size;

  const AnimatedFlutterBrowserLogo({
    super.key,
    this.animationDuration = const Duration(milliseconds: 1000),
    this.size = 100.0,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white, // هنا لون الخلفية أبيض، تقدر تغيره
      body: Center(
        child: Image.network(
          'https://english4us.co.uk/wp-content/uploads/2023/05/ii.png', // حط رابط لوجو أكاديميتك المباشر هنا مكان ده
          width: 200,
          height: 200,
        ),
      ),
    );
  }
}